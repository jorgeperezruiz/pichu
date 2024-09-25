package com.mekami.common_data

import com.mekami.common_data.provider.UrlProvider
import com.mekami.common_data.response.PokemonDto
import com.mekami.common_data.response.PokemonListResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import javax.inject.Inject

class PichuService
    @Inject
    constructor(
        private val urlProvider: UrlProvider,
        private val httpClientProvider: HttpClientProvider,
    ) {
        companion object {
            private const val KEY_LIMIT = "limit"

            private const val DEFAULT_PAGE_SIZE = 250
        }

        internal suspend fun getPokemon(id: Long): PokemonDto =
            httpClientProvider.getClient(forceRefresh = false)
                .get(urlProvider.getGameUrl()) {
                    url { appendPathSegments(id.toString()) }
                }.body()

        internal suspend fun getPokemonList(): PokemonListResponse =
            httpClientProvider.getClient(forceRefresh = false)
                .get(urlProvider.getGameUrl()) {
                    parameter(KEY_LIMIT, DEFAULT_PAGE_SIZE)
                }.body()
    }