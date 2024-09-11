package com.mekami.common_data.provider

import javax.inject.Inject


class UrlProvider @Inject constructor() {
        companion object {
            private const val URL_SCHEME = "https"

            private const val BASE_URL = "pokeapi.co/api/v2/pokemon"
        }

        private fun getBaseApiUrl(): String = "$URL_SCHEME://$BASE_URL"

        fun getGameUrl(): String = getBaseApiUrl()

    }
