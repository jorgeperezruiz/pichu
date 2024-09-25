package com.mekami.games

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.mekami.common_data.provider.DispatcherProvider
import com.mekami.common_domain.PichuError
import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.GameEntity
import com.mekami.common_domain.entity.SimpleGameEntity
import com.mekami.common_domain.usecase.GetAllGamesUseCase
import com.mekami.common_domain.usecase.GetGameUseCase
import com.mekami.games.screens.GameViewModel
import com.mekami.games.screens.GamesAction
import com.mekami.games.screens.GamesEffect
import com.mekami.games.screens.GamesViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description


class GameScreenViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getGameUseCase: GetGameUseCase = mockk()
    private val dispatcherProvider: DispatcherProvider = TestDispatcherProvider(mainDispatcherRule.testDispatcher)
    private val savedState: SavedStateHandle = mockk()

    private val vm by lazy {
        GameViewModel(getGameUseCase, dispatcherProvider, savedState)
    }

    @Before
    fun setUp() {
        coEvery { savedState.get<String>(any()) } returns "1"
    }

    @Test
    fun `given use case return a pokemon, state pokemon is updated`() = runTest {
        val id = 1L
        val name = "charmander"
        val spriteUrl = "url"
        val weight = 1L
        val height = 1L
        val pokemon =
            GameEntity(
                id = id,
                name = name,
                height = height,
                weight = weight,
                spriteUrl = spriteUrl,
                moves = null
            )
        coEvery { getGameUseCase.getGameWithId(any()) } returns PichuResult.Success(pokemon)
        vm.screenState.test {
            assertEquals(pokemon, awaitItem().game)
        }
    }

    @Test
    fun `given use case returns pokemon, state error is null`() = runTest {
        val id = 1L
        val name = "charmander"
        val spriteUrl = "url"
        val weight = 1L
        val height = 1L
        val pokemon =
            GameEntity(
                id = id,
                name = name,
                height = height,
                weight = weight,
                spriteUrl = spriteUrl,
                moves = null
            )
        coEvery { getGameUseCase.getGameWithId(any()) } returns PichuResult.Success(pokemon)
        vm.screenState.test {
            assertEquals(null, awaitItem().error)
        }
    }

    @Test
    fun `given use case returns pokemons, state loading is false`() = runTest {
        val id = 1L
        val name = "charmander"
        val spriteUrl = "url"
        val weight = 1L
        val height = 1L
        val pokemon =
            GameEntity(
                id = id,
                name = name,
                height = height,
                weight = weight,
                spriteUrl = spriteUrl,
                moves = null
            )
        coEvery { getGameUseCase.getGameWithId(any()) } returns PichuResult.Success(pokemon)
        vm.screenState.test {
            assertEquals(false, awaitItem().isLoading)
        }
    }

    @Test
    fun `given use case returns error, state error is updated`() = runTest {
        coEvery { getGameUseCase.getGameWithId(any()) } returns PichuResult.Failure(PichuError.Unknown)
        vm.screenState.test {
            assertEquals(PichuError.Unknown, awaitItem().error)
        }
    }
}