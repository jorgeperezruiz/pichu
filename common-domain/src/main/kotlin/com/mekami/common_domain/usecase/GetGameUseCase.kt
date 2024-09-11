package com.mekami.common_domain.usecase

import com.mekami.common_domain.repository.GameRepository
import javax.inject.Inject

class GetGameUseCase
    @Inject
    constructor(
        private val gameRepo: GameRepository,
    ) {
        suspend fun getGameWithId(
            id: Long,
        ) = gameRepo.getGame(id)
    }
