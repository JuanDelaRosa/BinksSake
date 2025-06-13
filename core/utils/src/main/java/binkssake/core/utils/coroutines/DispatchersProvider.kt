package binkssake.core.utils.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersProvider {

    val main: CoroutineDispatcher get() = Dispatchers.Main
    val default: CoroutineDispatcher get() = Dispatchers.Default
    val io: CoroutineDispatcher get() = Dispatchers.IO
    val unconfined: CoroutineDispatcher get() = Dispatchers.Unconfined
}

class DefaultDispatchersProvider : DispatchersProvider
