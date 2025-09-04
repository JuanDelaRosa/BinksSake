package akibaroom.feature.figures.data.api

import akibaroom.feature.figures.data.entity.FigurePageResponse
import akibaroom.feature.figures.data.entity.FigureResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<FigurePageResponse>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<FigureResponse>
}


