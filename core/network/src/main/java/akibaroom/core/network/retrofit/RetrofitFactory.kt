package akibaroom.core.network.retrofit

import akibaroom.core.network.NetworkingConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import akibaroom.core.network.okhttp.OkHttpFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

internal class RetrofitFactory(private val okHttpFactory: OkHttpFactory) {

    fun create(config: NetworkingConfig): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(config.baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpFactory.create(config))
            .build()
    }
}
