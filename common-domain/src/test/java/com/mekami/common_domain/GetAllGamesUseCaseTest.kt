package com.mekami.common_domain

import com.mekami.common_domain.entity.SimpleGameEntity
import com.mekami.common_domain.repository.GamesRepository
import com.mekami.common_domain.usecase.GetAllGamesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetAllGamesUseCaseTest {

    private val repository: GamesRepository = mockk()
    private lateinit var useCase: GetAllGamesUseCase

    @Before
    fun setUp() {
        useCase = GetAllGamesUseCase(repository)
    }

    @Test
    fun `given fetching games success then it returns the games`() = runTest {

        coEvery { repository.getGames() } returns PichuResult.Success(
            listOf(
                SimpleGameEntity(
                    1L,
                    "pokemon1"
                ),
                SimpleGameEntity(
                    2L,
                    "pokemon2"
                )
            )
        )

        val result = useCase.getGames()

        assertTrue(result is PichuResult.Success)
        assertEquals(
            listOf(
                SimpleGameEntity(1L, "pokemon1"),
                SimpleGameEntity(
                    2L,
                    "pokemon2"
                )
            ), result.getData()
        )
    }

    @Test
    fun `given fetching games fails then it returns the Failure`() = runTest {

        coEvery { repository.getGames() } returns PichuResult.Failure(PichuError.Unknown)

        val result = useCase.getGames()

        assertTrue(result is PichuResult.Failure)
    }

}