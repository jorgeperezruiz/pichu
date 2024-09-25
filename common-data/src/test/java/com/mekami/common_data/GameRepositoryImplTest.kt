package com.mekami.common_data

import com.mekami.common_data.mapper.GameMapper
import com.mekami.common_data.repository.GameRepositoryImpl
import com.mekami.common_data.response.GameDto
import com.mekami.common_data.response.Mfe
import com.mekami.common_data.response.Sprites
import com.mekami.common_data.response.Stat
import com.mekami.common_data.response.Type
import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.getData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

import org.junit.Assert.*
import org.junit.Test
import java.io.IOException

class GameRepositoryImplTest {
    private val pichuService = mockk<PichuService>()
    private val gameMapper = GameMapper()

    private val sut: GameRepositoryImpl by lazy {
        GameRepositoryImpl(pichuService, gameMapper)
    }

    @Test
    fun `given success response then data is mapped properly`() = runTest {

        val id = 1L
        val name = "charmander"
        val sprites = Sprites(frontDefault = "url")
        val weight = 1L
        val height = 1L

        // Given
        coEvery { pichuService.getGame(any()) } returns createPichuServiceResponse(
            id = id,
            name = name,
            sprites = sprites,
            weight = weight,
            height = height
        )

        // When
        val result = sut.getGame(id)

        assertTrue(result is PichuResult.Success)
        assertEquals(id, result.getData().id)
        assertEquals(name, result.getData().name)
        assertEquals(sprites.frontDefault, result.getData().spriteUrl)
        assertEquals(weight, result.getData().weight)
        assertEquals(height, result.getData().height)
    }

    @Test
    fun `given error response then error is returned`() = runTest {

        val id = 1L

        // Given
        coEvery { pichuService.getGame(any()) } coAnswers { throw IOException() }

        // When
        val result = sut.getGame(id)

        assertTrue(result is PichuResult.Failure)
    }
}

fun createPichuServiceResponse(
    id: Long = 0,
    moves: List<Mfe> = emptyList(),
    name: String = "charmander",
    order: Long = 7,
    sprites: Sprites = Sprites(frontDefault = ""),
    stats: List<Stat> = emptyList(),
    types: List<Type> = emptyList(),
    weight: Long = 10,
    height: Long = 10,
) = GameDto(
    id = id,
    moves = moves,
    name = name,
    order = order,
    sprites = sprites,
    stats = stats,
    types = types,
    weight = weight,
    height = height
)