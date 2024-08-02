package com.compose.mikasa.api

import com.compose.mikasa.model.CharactersModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("characters")
    suspend fun getCharactersTitans(): Response<CharactersModel>
}