package com.compose.mikasa.domain

import com.compose.mikasa.model.CharactersModel
import com.compose.mikasa.model.Info
import com.compose.mikasa.model.Result
import com.compose.mikasa.repository.ApiHelper
import com.compose.mikasa.repository.MiKasaRepository
import com.compose.mikasa.utils.ResourceState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GetCharactersTitansCaseTest {
    @RelaxedMockK
    private lateinit var apiHelper: ApiHelper

    private lateinit var miKasaRepository: MiKasaRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        miKasaRepository = MiKasaRepository(apiHelper)
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
        var myList = CharactersModel(Info(1, 1, ""), listCharacters)


        val myListSuccess = Response.success(myList)

        coEvery { apiHelper.getCharactersTitans() } returns myListSuccess

        miKasaRepository.getCharactersTitans().collect { response ->
            if (response is ResourceState.Success) {
                assertEquals(myList, response.data)
            }
        }
        coVerify(exactly = 1) { apiHelper.getCharactersTitans() }
    }

    @Test
    fun `getCharactersTitans returns Error when response is not successful`() = runBlocking {
        val errorResponse = Response.error<CharactersModel>(400, mockk(relaxed = true))
        coEvery { apiHelper.getCharactersTitans() } returns errorResponse

        miKasaRepository.getCharactersTitans().collect { response ->
            if (response is ResourceState.Error) {
                assertEquals("Something went wrong", (response).message)
            }
        }
        coVerify(exactly = 1) { apiHelper.getCharactersTitans() }
    }
}
