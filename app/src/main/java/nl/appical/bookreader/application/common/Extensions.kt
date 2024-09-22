package nl.appical.bookreader.application.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

inline fun <T, R> Flow<List<T>>.mapList(crossinline transform: (value: T) -> R) =
    transform { value ->
        return@transform emit(value.map(transform))
    }