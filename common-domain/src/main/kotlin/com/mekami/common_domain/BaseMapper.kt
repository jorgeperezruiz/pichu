package com.mekami.common_domain

abstract class BaseMapper<in From, To> {
    abstract fun mapFrom(from: From): To

    open fun mapFromList(from: List<From?>?) = from?.filterNotNull()?.map { mapFrom(it) } ?: emptyList()
}

abstract class TranslatedMapper<in From, To> {
    abstract fun mapFrom(
        from: From,
        shouldTranslate: Boolean,
    ): To

    open fun mapFromList(
        from: List<From?>?,
        shouldTranslate: Boolean,
    ) = from?.filterNotNull()?.map { mapFrom(it, shouldTranslate) } ?: emptyList()
}
