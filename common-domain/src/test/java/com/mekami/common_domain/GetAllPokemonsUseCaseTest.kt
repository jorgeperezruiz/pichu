package com.mekami.common_domain

import com.mekami.common_domain.entity.PokemonInListEntity
import com.mekami.common_domain.repository.PokemonListRepository
import com.mekami.common_domain.usecase.GetAllPokemonsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetAllPokemonsUseCaseTest {

    private val repository: PokemonListRepository = mockk()
    private lateinit var useCase: GetAllPokemonsUseCase

    @Before
    fun setUp() {
        useCase = GetAllPokemonsUseCase(repository)
    }

    @Test
    fun `given fetching games success then it returns the games`() = runTest {

        coEvery { repository.getPokemonList() } returns PichuResult.Success(
            listOf(
                PokemonInListEntity(
                    1L,
                    "pokemon1"
                ),
                PokemonInListEntity(
                    2L,
                    "pokemon2"
                )
            )
        )

        val result = useCase.getGames()

        assertTrue(result is PichuResult.Success)
        assertEquals(
            listOf(
                PokemonInListEntity(1L, "pokemon1"),
                PokemonInListEntity(
                    2L,
                    "pokemon2"
                )
            ), result.getData()
        )
    }

    @Test
    fun `given fetching games fails then it returns the Failure`() = runTest {

        coEvery { repository.getPokemonList() } returns PichuResult.Failure(PichuError.Unknown)

        val result = useCase.getGames()

        assertTrue(result is PichuResult.Failure)
    }

}