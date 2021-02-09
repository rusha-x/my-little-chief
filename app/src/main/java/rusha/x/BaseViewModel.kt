package rusha.x

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val showRetryLiveEvent = SingleLiveEvent<() -> Unit>()
    val scope = CoroutineScope(context = viewModelScope.coroutineContext)

    protected fun launchWithRetry(block: suspend CoroutineScope.() -> Unit) {
        scope.launch(
            context = CoroutineExceptionHandler { _, _ ->
                showRetryLiveEvent.value = {
                    launchWithRetry(block)
                }
            },
            block = block
        )
    }

}