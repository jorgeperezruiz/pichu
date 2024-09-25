package com.mekami.common_data

import com.mekami.common_data.mapper.SimpleGameMapper
import com.mekami.common_data.repository.GamesRepositoryImpl
import com.mekami.common_data.response.GamesResponse
import com.mekami.common_data.response.SimpleGameDto
import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.getData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class GamesRepositoryImplTest {
    private val pichuService = mockk<PichuService>()
    private val gamesMapper = SimpleGameMapper()

    private val sut: GamesRepositoryImpl by lazy {
        GamesRepositoryImpl(pichuService, gamesMapper)
    }

    @Test
    fun `given success response then data is mapped properly`() = runTest {

        val pokemonList = listOf(
            SimpleGameDto("charmander", "charmander_url/1"),
            SimpleGameDto("pichu", "pichu_url/2"),
            SimpleGameDto("bulbasur", "bulbasur_url/3"),
        )

        // Given
        coEvery { pichuService.getGames() } returns createPichuServiceResponse(
            pokemonList = pokemonList
        )

        // When
        val result = sut.getGames()

        assertTrue(result is PichuResult.Success)
        assertEquals(3, result.getData().size)
        assertEquals("charmander", result.getData()[0].name)
        assertEquals(1, result.getData()[0].id)
        assertEquals("pichu", result.getData()[1].name)
        assertEquals(2, result.getData()[1].id)
        assertEquals("bulbasur", result.getData()[2].name)
        assertEquals(3, result.getData()[2].id)
    }

    @Test
    fun `given error response then error is returned`() = runTest {

        // Given
        coEvery { pichuService.getGames() } coAnswers { throw IOException() }

        // When
        val result = sut.getGames()

        assertTrue(result is PichuResult.Failure)
    }
}

fun createPichuServiceResponse(
    pokemonList: List<SimpleGameDto> = listOf(
        SimpleGameDto("charmander", "charmander_url/1"),
        SimpleGameDto("pichu", "pichu_url/2"),
        SimpleGameDto("bulbasur", "bulbasur_url/3"),
    )
) = GamesResponse(
    count = pokemonList.count(),
    results = pokemonList
)