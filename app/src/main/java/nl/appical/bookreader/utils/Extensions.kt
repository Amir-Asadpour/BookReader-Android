package nl.appical.bookreader.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

public inline fun <T, R> Flow<List<T>>.mapList(crossinline transform: (value: T) -> R) =
    transform { value ->
        return@transform emit(value.map(transform))
    }