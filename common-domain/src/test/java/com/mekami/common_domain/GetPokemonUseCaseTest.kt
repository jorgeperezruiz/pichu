package com.mekami.common_domain

import com.mekami.common_domain.entity.PokemonEntity
import com.mekami.common_domain.repository.PokemonRepository
import com.mekami.common_domain.usecase.GetPokemonUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetPokemonUseCaseTest {

    private val repository: PokemonRepository = mockk()
    private lateinit var useCase: GetPokemonUseCase

    @Before
    fun setUp() {
        useCase = GetPokemonUseCase(repository)
    }

    @Test
    fun `given fetching game success then it returns the game`() = runTest {

        val id = 1L
        val name = "charmander"
        val spriteUrl = "url"
        val weight = 1L
        val height = 1L

        coEvery { repository.getPokemon(id) } returns PichuResult.Success(
            PokemonEntity(
                id = id,
                name = name,
                height = height,
                weight = weight,
                spriteUrl = spriteUrl,
                moves = null
            )
        )

        val result = useCase.getPokemonWithId(id)

        assertTrue(result is PichuResult.Success)
        assertEquals(
            PokemonEntity(
                id = id,
                name = name,
                height = height,
                weight = weight,
                spriteUrl = spriteUrl,
                moves = null
            ), result.getData()
        )
    }

    @Test
    fun `given fetching a game fails then it returns the Failure`() = runTest {

        coEvery { repository.getPokemon(any()) } returns PichuResult.Failure(PichuError.Unknown)

        val result = useCase.getPokemonWithId(0L)

        assertTrue(result is PichuResult.Failure)
    }

}