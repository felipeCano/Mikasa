package com.compose.mikasa.repository

import com.compose.mikasa.api.ApiServices
import com.compose.mikasa.model.CharactersModel
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiServices) : ApiHelper {
    override suspend fun getCharactersTitans(): Response<CharactersModel> {
        return apiService.getCharactersTitans()
    }
}