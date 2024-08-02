package com.compose.mikasa.repository

import com.compose.mikasa.model.CharactersModel
import retrofit2.Response

interface ApiHelper {
    suspend fun getCharactersTitans(): Response<CharactersModel>
}