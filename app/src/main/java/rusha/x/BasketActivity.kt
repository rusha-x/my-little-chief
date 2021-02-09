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
import kotlinx.android.synthetic.main.basket_fragment.*
import kotlinx.android.synthetic.main.basket_item.view.*
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.kodein.di.instance

class BasketViewModel : BaseViewModel() {
    private val json by di.instance<Json>()
    private val httpClient by di.instance<HttpClient>()

    val basketItemsLiveData = MutableLiveData<List<Basket.Item>>(emptyList())
    val goToProductDetails = SingleLiveEvent<Product>()

    init {
        launchWithRetry {
            val basketJson = httpClient.get<String>(
                "https://gist.githubusercontent.com/adevone/" +
                        "440aeb6101f17075c79282a3f054a6ed/raw/" +
                        "ca849ec514eea9f5a2b2a3db8239c04f97df0b96/basket.json"
            )
            val basket: Basket = json.parse(
                deserializer = Basket.serializer(),
                string = basketJson
            )

            basketItemsLiveData.value = basket.items
        }
    }

    fun onBasketItemClick(item: Basket.Item) {
        goToProductDetails.value = item.product
    }
}

// класс BasketActivity наследует все свойства AppCompatActivity т.е все его переменные и функции
// TODO для каждого BasketActivity, являющегося AppCompatActivity
class BasketFragment : Fragment(R.layout.basket_fragment) {
    private lateinit var viewModel: BasketViewModel

    // мы переопределяем onCreate, чтобы сделать доп. действия при создании активити
    // TODO определяем, что в отличие от других AppCompatActivity, BasketActivity onCreate
    override fun onCreate(
        // TODO , принимая savedInstanceState, являющееся необязательным Bundle,
        savedInstanceState: Bundle?
    ) {
        // TODO сначала делает onCreate как AppCompatActivity с savedInstanceState
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BasketViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.basketItemsLiveData.observe(viewLifecycleOwner, Observer { basketItems ->
            //создание адаптера-itemViewAdapter, кладем обьект(который ведет себя так как мы описали
            // в классе BasketListAdapter   типа BasketListAdapter в эту переменную
            //правила работы которого описаны в BasketListAdapter
            //TODO itemViewAdapter будет получен в результате создания BasketListAdapter
            val itemViewAdapter = BasketListAdapter(viewModel)

            // говорим какой список элементов корзины будет отображать тот адаптер который описали выше.
            // А в itemsToAdopt лежит потенциальный список ингр.
            // TODO itemsToAdopt itemViewAdapter(-a) это теперь тоже самое что и basketItems
            itemViewAdapter.itemsToAdopt = basketItems

            //basketRecyclerView- это название и обращение к списку элементов корзины в xml
            //это переменная, в которой лежит описание того как отображаются ячейки списка
            //в переменную basketRecyclerView мы записываем тот адаптер который создали выше
            //TODO adapter basketRecyclerView тоже самое itemViewAdapter
            basketRecyclerView.adapter = itemViewAdapter
        })

        viewModel.goToProductDetails.observe(viewLifecycleOwner, Observer { product ->
            findNavController().navigate(
                BasketFragmentDirections.actionBasketFragmentToProductDetailsFragment(product)
            )
        })
    }
}

class BasketListAdapter (
    private val viewModel: BasketViewModel
): RecyclerView.Adapter<BasketListAdapter.ItemViewHolder>() {

    var itemsToAdopt: List<Basket.Item> = listOf()
    override fun getItemCount(): Int {
        return itemsToAdopt.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BasketListAdapter.ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context) // была опечатка. LayoutInfla-y-ter
        val view = inflater.inflate(
            R.layout.basket_item,
            parent,
            false
        )
        return ItemViewHolder(containerView = view)
    }

    override fun onBindViewHolder(holder: BasketListAdapter.ItemViewHolder, position: Int) {
        val itemOnPosition = itemsToAdopt.get(index = position)
        holder.bind(item = itemOnPosition)
    }

    inner class ItemViewHolder(
        val containerView: View
    ) : RecyclerView.ViewHolder(containerView) {
        fun bind(item: Basket.Item) {
            containerView.setOnClickListener {
                viewModel.onBasketItemClick(item)
            }
            containerView.basketItemView.text = item.product.name
            containerView.basketCountView.text = item.count.toString()
        }
    }
}


























