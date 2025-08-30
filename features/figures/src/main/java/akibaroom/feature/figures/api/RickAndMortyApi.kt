package akibaroom.feature.figures.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import kotlinx.serialization.Serializable

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterPageResponse

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterResponse
}

@Serializable
data class CharacterPageResponse(
    val info: PageInfo,
    val results: List<CharacterResponse>
)

@Serializable
data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

@Serializable
data class CharacterResponse(
    val id: Int,
    val name: String,
    val image: String
)


