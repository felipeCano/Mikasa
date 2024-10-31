package com.compose.mikasa.repository

import com.compose.mikasa.model.CharactersModel
import com.compose.mikasa.utils.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MiKasaRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getCharactersTitans(): Flow<ResourceState<CharactersModel>> {
        return flow {
            emit(ResourceState.Loading())
            val response = apiHelper.getCharactersTitans()
            if (response.isSuccessful) {
                response.body()!!.let {
                    emit(ResourceState.Success(it))
                }
            } else {
                emit(ResourceState.Error("Something went wrong"))
            }
        }
    }
}