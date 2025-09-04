package akibaroom.core.network

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

data class NetworkingConfig(
    val baseUrl: String = "https://rickandmortyapi.com/api/",
    val readTimeout: Duration = 30.toDuration(DurationUnit.SECONDS),
    val writeTimeout: Duration = 30.toDuration(DurationUnit.SECONDS),
    val connectTimeout: Duration = 10.toDuration(DurationUnit.SECONDS),
    val callTimeout: Duration = 0.toDuration(DurationUnit.SECONDS),
    val useAnalytics: Boolean = true,
    val useLogger: Boolean = true,
    val useBasicHeaders: Boolean = true,
    val useOfflineCache: Boolean = false,
    val useHCTimeSerializer: Boolean = false,
    val useUnauthorizedInterceptor: Boolean = true,
    val useGsonNullSerialization: Boolean = false,
    val useDefaultCookieJar: Boolean = false,
)
