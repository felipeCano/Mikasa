package com.compose.mikasa.domain

import com.compose.mikasa.model.CharactersModel
import com.compose.mikasa.model.Info
import com.compose.mikasa.model.Result
import com.compose.mikasa.repository.ApiHelper
import com.compose.mikasa.repository.MiKasaRepository
import com.compose.mikasa.utils.ResourceState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharactersTitansCaseTest {
    @RelaxedMockK
    private lateinit var apiHelper: ApiHelper

    @RelaxedMockK
    private lateinit var miKasaRepository: MiKasaRepository

    private var logTest = ""

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        //miKasaRepository = MiKasaRepository(apiHelper)
        mockk<MiKasaRepository>().also { mockito ->
            miKasaRepository = mockito
        }
    }

    @Test
    fun test() {
        logTest = miKasaRepository.testFunc()
        println("Hola soy un test $logTest")
    }

    @Test
    fun testCharactersTitans() = runBlocking {
        var listCharacters = listOf(
            Result(
                1,
                "name",
                "url",
                emptyList(),
                emptyList(),
                "gender",
                "age",
                "height",
                emptyList(),
                "brithplace",
                "residence",
                "status",
                "occupation",
                emptyList(),
                emptyList(),
                emptyList()
            )
        )
        var myList = flowOf(
            ResourceState.Success(CharactersModel(Info(1, 1, ""), listCharacters))
        )
        coEvery { miKasaRepository.getCharactersTitans() } returns myList
        val response = miKasaRepository.getCharactersTitans()
        assert(response == myList)
    }
}