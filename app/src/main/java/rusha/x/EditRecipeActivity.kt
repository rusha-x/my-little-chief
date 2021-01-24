package rusha.x

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.put
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.android.synthetic.main.edit_recipe_activity.*
import kotlinx.android.synthetic.main.edit_recipe_ingredient.view.*
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.TT
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.instance
import org.kodein.di.singleton
import summer.android.SummerActivity
import summer.android.SummerFragment

interface EditRecipeView {
    var ingredients: List<CreateOrEditRecipe.Ingredient>
    var recipeName: String
    var recipeDescription: String
}

class EditRecipeViewPresenter : BasePresenter<EditRecipeView>() {
    private val json by di.instance<Json>()
    private val httpClient by di.instance<HttpClient>()

    override val viewProxy = object : EditRecipeView {
        override var ingredients by state({ it::ingredients }, initial = emptyList())
        override var recipeName by state({ it::recipeName }, initial = "")
        override var recipeDescription by state({ it::recipeDescription }, initial = "")
    }

    override fun onEnter() {
        super.onEnter()

        val initialIngredients: List<CreateOrEditRecipe.Ingredient> = listOf(
            CreateOrEditRecipe.Ingredient(
                countInRecipe = 3.0,
                product = CreateOrEditProductByName(
                    name = "trupDevstvenici",
                    price = 5.0,
                    unit = "u."
                )
            ),
            CreateOrEditRecipe.Ingredient(
                countInRecipe = 5.0,
                product = CreateOrEditProductByName(
                    name = "nozgik",
                    price = 1.0,
                    unit = "u."
                )
            )
        )

        viewProxy.ingredients = initialIngredients
    }

    fun onIngredientPlusClick(ingredient: CreateOrEditRecipe.Ingredient) {
        viewProxy.ingredients = viewProxy.ingredients.map { currentIngredient ->
            if (currentIngredient == ingredient)
                currentIngredient.withIncrementCount()
            else
                currentIngredient
        }
    }

    fun onIngredientMinusClick(ingredient: CreateOrEditRecipe.Ingredient) {
        viewProxy.ingredients = viewProxy.ingredients.map { currentIngredient ->
            if (currentIngredient == ingredient)
                currentIngredient.withDecrementCount()
            else
                currentIngredient
        }
    }

    fun onSetIngredientCount(ingredient: CreateOrEditRecipe.Ingredient, count: Double) {
        viewProxy.ingredients = viewProxy.ingredients.map { currentIngredient ->
            if (currentIngredient == ingredient)
                currentIngredient.withCount(count)
            else
                currentIngredient
        }
    }

    private var recipeName: String = ""
    fun onSetRecipeName(name: String) {
        recipeName = name
    }

    private var recipeDescription: String = ""
    fun onSetRecipeDescription(description: String) {
        recipeDescription = description
    }

    fun onSaveClick() {
        launch(block = {
            val createOrEditRecipe = CreateOrEditRecipe(
                name = recipeName,
                description = recipeDescription,
                ingredients = viewProxy.ingredients
            )
            val createOrEditRecipeJson = json.stringify(
                CreateOrEditRecipe.serializer(),
                createOrEditRecipe
            )
            httpClient.put<Unit>("$host/recipe") {
                contentType(ContentType.Application.Json)
                body = createOrEditRecipeJson
            }
            //finish()
        })
    }
}

class EditRecipeFragment : SummerFragment(), EditRecipeView {

    private val presenter by bindPresenter { EditRecipeViewPresenter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.edit_recipe_activity,
            container,
            false
        )
    }

    override var ingredients: List<CreateOrEditRecipe.Ingredient> by didSet {
        ingredientsViewAdapter.ingredientsToAdopt = ingredients
        ingredientsViewAdapter.notifyDataSetChanged()
    }

    override var recipeName: String by didSet {
        nameRecipeEdit.setText(recipeName)
    }

    override var recipeDescription: String by didSet {
        descriptionRecipeEdit.setText(recipeDescription)
    }

    private lateinit var ingredientsViewAdapter: EditRecipeIngredientsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save.setOnClickListener {
            presenter.onSaveClick()
        }

        nameRecipeEdit.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val name = nameRecipeEdit.text.toString()
                presenter.onSetRecipeName(name)
            }
        }

        descriptionRecipeEdit.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val description = descriptionRecipeEdit.text.toString()
                presenter.onSetRecipeDescription(description)
            }
        }

        ingredientsViewAdapter = EditRecipeIngredientsAdapter(presenter)
        editRecipeIngredientsView.adapter = ingredientsViewAdapter
    }
}

class EditRecipeIngredientsAdapter(
    private val presenter: EditRecipeViewPresenter
) : RecyclerView.Adapter<EditRecipeIngredientsAdapter.IngredientViewHolder>() {

    var ingredientsToAdopt: List<CreateOrEditRecipe.Ingredient> = emptyList()
    override fun getItemCount(): Int {
        return ingredientsToAdopt.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.edit_recipe_ingredient,
            parent,
            false
        )
        return IngredientViewHolder(containerView = view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredientOnPosition = ingredientsToAdopt.get(index = position)
        holder.bind(ingredient = ingredientOnPosition)
    }

    inner class IngredientViewHolder(
        val containerView: View
    ) : RecyclerView.ViewHolder(containerView) {

        fun bind(ingredient: CreateOrEditRecipe.Ingredient) {
            containerView.addIngredient.setText(ingredient.product.name)
            containerView.ingredientCount.setText(ingredient.countInRecipe.toString())

            containerView.plus.setOnClickListener {
                presenter.onIngredientPlusClick(ingredient)
            }

            containerView.minus.setOnClickListener {
                presenter.onIngredientMinusClick(ingredient)
            }

            containerView.ingredientCount.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    val count = containerView.ingredientCount.text.toString().toDouble()
                    presenter.onSetIngredientCount(ingredient, count)
                }
            }
        }
    }
}

val di = DI {

    bind<Json>() with singleton {
        /**
         * Создаём сервис сериализации и десериализации JSON,
         * сконфигурированный стабильной конфигурацией
         *
         * Стабильная конфигуация - это то, что в библиотеке kotlinx.serialization
         * уже стабильно работает
         *
         * Объект такого типа можно использовать для сколько угодно многих JSON-ов
         */
        Json {
            ignoreUnknownKeys = true
        }
    }

    bind<HttpClient>() with singleton {
        HttpClient(OkHttp)
    }
}