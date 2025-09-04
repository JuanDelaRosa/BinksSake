package akibaroom.core.network.retrofit

import akibaroom.core.network.NetworkingConfig
import akibaroom.core.utils.enviroment.AppContextProvider
import akibaroom.core.network.okhttp.OkHttpFactory
import retrofit2.Retrofit

internal class RetrofitProvider {

    private val retrofits: MutableMap<NetworkingConfig, Retrofit> = mutableMapOf()

    fun get(config: NetworkingConfig): Retrofit = retrofits[config] ?: create(config)

    private fun create(config: NetworkingConfig) = RetrofitFactory(
        OkHttpFactory(AppContextProvider.context)).create(config).also {
            retrofits[config] = it
        }
}
