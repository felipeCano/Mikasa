package com.compose.mikasa.repository

import android.util.Log
import com.compose.mikasa.model.CharactersModel
import com.compose.mikasa.utils.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MiKasaRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    /* suspend fun getCharactersTitans() : Response<CharactersModel> {
         return apiHelper.getCharactersTitans()
     }*/

    suspend fun getCharactersTitans(): Flow<ResourceState<CharactersModel>> {
        return flow {
            emit(ResourceState.Loading())
            val response = apiHelper.getCharactersTitans()
            (if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    Log.d("InsideRepository", "$it")
                    emit(ResourceState.Success(it))
                }
            }else {
                emit(ResourceState.Error("Something went wrong"))
            })!!
        }.catch { e ->
            emit(ResourceState.Error(e?.localizedMessage ?: "Something went wrong"))
        }
    }

}