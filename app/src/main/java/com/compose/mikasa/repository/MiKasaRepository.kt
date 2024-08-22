package com.compose.mikasa.repository

import android.util.Log
import com.compose.mikasa.model.CharactersModel
import com.compose.mikasa.utils.Constants.TEXT
import com.compose.mikasa.utils.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MiKasaRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {

    fun testFunc(): String {
        println(TEXT)
        return "this is a test for el test"
    }

    suspend fun getCharactersTitans(): Flow<ResourceState<CharactersModel>> {
        return flow {
            emit(ResourceState.Loading())
            val response = apiHelper.getCharactersTitans()
            (if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    Log.d("InsideRepository", "$it")
                    emit(ResourceState.Success(it))
                }
            } else {
                emit(ResourceState.Error("Something went wrong"))
            })!!
        }
    }
}