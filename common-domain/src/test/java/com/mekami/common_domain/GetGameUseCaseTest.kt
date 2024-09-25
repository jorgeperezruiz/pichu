package com.mekami.common_domain

import com.mekami.common_domain.entity.GameEntity
import com.mekami.common_domain.repository.GameRepository
import com.mekami.common_domain.usecase.GetGameUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetGameUseCaseTest {

    private val repository: GameRepository = mockk()
    private lateinit var useCase: GetGameUseCase

    @Before
    fun setUp() {
        useCase = GetGameUseCase(repository)
    }

    @Test
    fun `given fetching game success then it returns the game`() = runTest {

        val id = 1L
        val name = "charmander"
        val spriteUrl = "url"
        val weight = 1L
        val height = 1L

        coEvery { repository.getGame(id) } returns PichuResult.Success(
            GameEntity(
                id = id,
                name = name,
                height = height,
                weight = weight,
                spriteUrl = spriteUrl,
                moves = null
            )
        )

        val result = useCase.getGameWithId(id)

        assertTrue(result is PichuResult.Success)
        assertEquals(
            GameEntity(
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

        coEvery { repository.getGame(any()) } returns PichuResult.Failure(PichuError.Unknown)

        val result = useCase.getGameWithId(0L)

        assertTrue(result is PichuResult.Failure)
    }

}