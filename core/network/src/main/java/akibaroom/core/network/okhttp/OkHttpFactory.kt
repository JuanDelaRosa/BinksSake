package akibaroom.core.network.okhttp

import akibaroom.core.network.NetworkingConfig
import android.content.Context
import akibaroom.core.network.okhttp.OkHttpCache.addCache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpFactory(
    private val context: Context
) {

    fun create(config: NetworkingConfig): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addCache(context)

        builder
            .readTimeout(config.readTimeout.inWholeMilliseconds, TimeUnit.MILLISECONDS)
            .writeTimeout(config.writeTimeout.inWholeMilliseconds, TimeUnit.MILLISECONDS)
            .callTimeout(config.callTimeout.inWholeMilliseconds, TimeUnit.MILLISECONDS)
            .connectTimeout(config.connectTimeout.inWholeMilliseconds, TimeUnit.MILLISECONDS)

        return builder
            .build()
    }
}
