package com.mekami.common.navigation

import android.net.Uri

interface Destination {
    val routeDestination: String
    val deepLinkDestination get() = DEEP_LINK_SCHEMA.plus(routeDestination).addParamsOrBlank()
    val params: Map<String, Any> get() = mapOf()
    val routeDestinationWithParams get() = routeDestination.addParamsOrBlank()

    companion object {
        const val DEEP_LINK_SCHEMA = "spl://"
        const val EXTERNAL_DEEP_LINK_SCHEMA = "https://"
    }

    private fun String.addParamsOrBlank(): String {
        var routeWithParams = this
        val params = params.toMutableMap()

        // Replace mandatory params within the route path
        params.keys.forEach { key ->
            if (routeWithParams.contains("{$key}")) {
                routeWithParams = routeWithParams.replace("{$key}", params[key].toString())
                params.remove(key)
            }
        }

        // Append optional params at the end
        return routeWithParams.plus(
            params.map { (key, value) -> "$key=${Uri.encode(value.toString()) ?: ""}" }
                .joinToString("&")
                .let { if (it.isNotBlank()) "?$it" else "" },
        )
    }
}
