package com.mekami.games

import app.cash.turbine.test
import com.mekami.common_data.provider.DispatcherProvider
import com.mekami.common_domain.PichuError
import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.SimpleGameEntity
import com.mekami.common_domain.usecase.GetAllGamesUseCase
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


class GamesScreenViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getGamesUseCase: GetAllGamesUseCase = mockk()
    private val dispatcherProvider: DispatcherProvider = TestDispatcherProvider(mainDispatcherRule.testDispatcher)

    private val vm by lazy {
        GamesViewModel(getGamesUseCase, dispatcherProvider)
    }

    @Before
    fun setUp() {
        // no op
    }

    @Test
    fun `given user clicks in one pokemon then the effect is launched`() = runTest {
        coEvery { getGamesUseCase.getGames() } returns PichuResult.Success(emptyList())
        vm.effect.test {
            vm.setAction(GamesAction.OnPokeClick(1))
            assertEquals(GamesEffect.GoToGameScreen(1), awaitItem())
        }
    }

    @Test
    fun `given use case returns pokemons, state list is updated`() = runTest {
        val pokemonList = listOf(
            SimpleGameEntity(
                1L,
                "pokemon1"
            ),
            SimpleGameEntity(
                2L,
                "pokemon2"
            )
        )
        coEvery { getGamesUseCase.getGames() } returns PichuResult.Success(pokemonList)
        vm.screenState.test {
            assertEquals(pokemonList, awaitItem().games)
        }
    }

    @Test
    fun `given use case returns pokemons, state error is null`() = runTest {
        val pokemonList = listOf(
            SimpleGameEntity(
                1L,
                "pokemon1"
            ),
            SimpleGameEntity(
                2L,
                "pokemon2"
            )
        )
        coEvery { getGamesUseCase.getGames() } returns PichuResult.Success(pokemonList)
        vm.screenState.test {
            assertEquals(null, awaitItem().error)
        }
    }

    @Test
    fun `given use case returns pokemons, state loading is false`() = runTest {
        val pokemonList = listOf(
            SimpleGameEntity(
                1L,
                "pokemon1"
            ),
            SimpleGameEntity(
                2L,
                "pokemon2"
            )
        )
        coEvery { getGamesUseCase.getGames() } returns PichuResult.Success(pokemonList)
        vm.screenState.test {
            assertEquals(false, awaitItem().isLoading)
        }
    }

    @Test
    fun `given use case returns error, state error is updated`() = runTest {
        coEvery { getGamesUseCase.getGames() } returns PichuResult.Failure(PichuError.Unknown)
        vm.screenState.test {
            assertEquals(PichuError.Unknown, awaitItem().error)
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

class TestDispatcherProvider(private val dispatcher: CoroutineDispatcher) : DispatcherProvider {
    override fun ui(): CoroutineDispatcher = dispatcher
    override fun computation(): CoroutineDispatcher = dispatcher
    override fun io(): CoroutineDispatcher = dispatcher
}
