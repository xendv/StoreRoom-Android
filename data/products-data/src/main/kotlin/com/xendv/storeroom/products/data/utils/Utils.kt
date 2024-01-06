package com.xendv.storeroom.products.data.utils

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.flow.stateIn
import java.io.IOException
import kotlin.math.exp
import kotlin.time.Duration.Companion.seconds

fun <T> Flow<List<T>>.cachedIn(scope: CoroutineScope) = catch {
    Log.e(LOG_TAG, "Wrapped request failed", it)
}.retryWhen { cause, attempt ->
    if (cause is IOException && attempt < NUM_RETRIES) {
        delay(exp(attempt.toDouble()).seconds)
        return@retryWhen true
    } else {
        return@retryWhen false
    }
}.stateIn(
    scope = scope,
    started = SharingStarted.Lazily,
    initialValue = emptyList(),
)

private const val LOG_TAG = "StateHolder"
const val NUM_RETRIES = 2