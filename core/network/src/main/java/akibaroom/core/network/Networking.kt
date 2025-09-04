package akibaroom.core.network

import akibaroom.core.network.retrofit.RetrofitProvider
import kotlinx.coroutines.DelicateCoroutinesApi

@OptIn(DelicateCoroutinesApi::class)
object Networking {
    private val retrofitProvider = RetrofitProvider()
    val defaultNetworkingConfig by lazy { NetworkingConfig() }

    fun <T> createService(serviceClass: Class<T>, networkingConfig: NetworkingConfig = defaultNetworkingConfig): T =
        retrofitProvider.get(networkingConfig).create(serviceClass)

    inline fun <reified T : Any> createService(networkingConfig: NetworkingConfig = defaultNetworkingConfig): T =
        createService(T::class.java, networkingConfig)
}
