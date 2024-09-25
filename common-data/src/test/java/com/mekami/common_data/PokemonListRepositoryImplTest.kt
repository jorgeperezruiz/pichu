package com.mekami.common_data

import com.mekami.common_data.mapper.SimpleGameMapper
import com.mekami.common_data.repository.PokemonListRepositoryImpl
import com.mekami.common_data.response.PokemonListResponse
import com.mekami.common_data.response.PokemonInListDto
import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.getData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class PokemonListRepositoryImplTest {
    private val pichuService = mockk<PichuService>()
    private val gamesMapper = SimpleGameMapper()

    private val sut: PokemonListRepositoryImpl by lazy {
        PokemonListRepositoryImpl(pichuService, gamesMapper)
    }

    @Test
    fun `given success response then data is mapped properly`() = runTest {

        val pokemonList = listOf(
            PokemonInListDto("charmander", "charmander_url/1"),
            PokemonInListDto("pichu", "pichu_url/2"),
            PokemonInListDto("bulbasur", "bulbasur_url/3"),
        )

        // Given
        coEvery { pichuService.getPokemonList() } returns createPichuServiceResponse(
            pokemonList = pokemonList
        )

        // When
        val result = sut.getPokemonList()

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
        coEvery { pichuService.getPokemonList() } coAnswers { throw IOException() }

        // When
        val result = sut.getPokemonList()

        assertTrue(result is PichuResult.Failure)
    }
}

fun createPichuServiceResponse(
    pokemonList: List<PokemonInListDto> = listOf(
        PokemonInListDto("charmander", "charmander_url/1"),
        PokemonInListDto("pichu", "pichu_url/2"),
        PokemonInListDto("bulbasur", "bulbasur_url/3"),
    )
) = PokemonListResponse(
    count = pokemonList.count(),
    results = pokemonList
)