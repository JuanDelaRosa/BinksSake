package akibaroom.core.network.okhttp

import android.content.Context
import android.util.Log
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

@Suppress("TooGenericExceptionCaught")
object OkHttpCache {
    private const val CACHE_DIR = "http_cache"
    private const val CACHE_SIZE = 200L * 1024L * 1024L // 200 MB
    private var cache: Cache? = null

    fun OkHttpClient.Builder.addCache(context: Context): OkHttpClient.Builder {
        try {
            cache = cache ?: Cache(
                directory = File(context.cacheDir, CACHE_DIR),
                maxSize = CACHE_SIZE
            )
            this.cache(cache)
        } catch (e: Exception) {
            Log.e("", e.message ?: "")
        }
        return this
    }
}
