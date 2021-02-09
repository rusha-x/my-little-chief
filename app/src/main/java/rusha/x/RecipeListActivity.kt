package rusha.x

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.android.synthetic.main.recipe_list_fragment.*
import kotlinx.android.synthetic.main.recipe_list_item.view.*
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import org.kodein.di.instance

class RecipeListViewModel : BaseViewModel() {
    private val json by di.instance<Json>()
    private val httpClient by di.instance<HttpClient>()

    val recipesLiveData = MutableLiveData<List<Recipe>>(emptyList())
    val isRefreshingLiveData = MutableLiveData<Boolean>()
    val goToEditRecipe = SingleLiveEvent<Nothing>()
    val goToRecipeDetails = SingleLiveEvent<Recipe>()

    fun onRefresh() {
        updateRecipes()
    }

    fun onResume() {
        updateRecipes()
    }

    private fun updateRecipes() {
        launchWithRetry {
            isRefreshingLiveData.value = true
            val recipesJson = httpClient.get<String>(
                "$host/recipe/all"
            )
            val allRecipes = json.parse(
                deserializer = Recipe.serializer().list,
                string = recipesJson
            )
            recipesLiveData.value = allRecipes
            isRefreshingLiveData.value = false
        }
    }

    fun onAddRecipeClick() {
        goToEditRecipe.call()
    }

    fun onRecipeClick(recipe: Recipe) {
        goToRecipeDetails.value = recipe
    }
}

class RecipeListFragment : Fragment(R.layout.recipe_list_fragment) {
    private lateinit var viewModel: RecipeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addRecipeButton.setOnClickListener {
            viewModel.onAddRecipeClick()
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        viewModel.recipesLiveData.observe(viewLifecycleOwner, Observer { recipes ->
            val recipesViewAdapter = RecipesListAdapter(viewModel)
            recipesViewAdapter.recipesToAdopt = recipes
            recipesView.adapter = recipesViewAdapter
        })

        viewModel.isRefreshingLiveData.observe(viewLifecycleOwner, Observer { isRefreshing ->
            swipeRefreshLayout.isRefreshing = isRefreshing
        })
        viewModel.goToEditRecipe.observe(viewLifecycleOwner, Observer { goToEditRecipe ->
            findNavController().navigate(
                RecipeListFragmentDirections.actionRecipeListFragmentToEditRecipeFragment()
            )
        })
        viewModel.goToRecipeDetails.observe(viewLifecycleOwner, Observer { recipe: Recipe ->
            findNavController().navigate(
                RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailsFragment(recipe)
            )
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}


class RecipesListAdapter(
    private val viewModel: RecipeListViewModel
) : RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {
    var recipesToAdopt: List<Recipe> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipesListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.recipe_list_item,
            parent,
            false
        )
        return ViewHolder(cellView = view)
    }

    override fun getItemCount(): Int {
        return recipesToAdopt.size
    }

    override fun onBindViewHolder(holder: RecipesListAdapter.ViewHolder, position: Int) {
        val recipeOnPosition = recipesToAdopt.get(index = position)
        holder.bind(recipe = recipeOnPosition)
    }

    inner class ViewHolder(
        val cellView: View
    ) : RecyclerView.ViewHolder(cellView) {

        fun bind(recipe: Recipe) {
            cellView.recipesItem.text = recipe.name
            cellView.setOnClickListener {
                viewModel.onRecipeClick(recipe)
            }
        }
    }
}
