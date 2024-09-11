package com.mekami.common_domain.usecase

import com.mekami.common_domain.repository.GamesRepository
import javax.inject.Inject

class GetAllGamesUseCase
    @Inject
    constructor(
        private val gamesRepo: GamesRepository,
    ) {
        suspend fun getGames() = gamesRepo.getGames()
    }
