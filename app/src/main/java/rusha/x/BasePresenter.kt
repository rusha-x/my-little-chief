package rusha.x

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import summer.SummerPresenter

abstract class BasePresenter<TView> : SummerPresenter<TView>(), CoroutineScope {
    override val coroutineContext = Dispatchers.Main
}