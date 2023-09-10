package com.kmmloginscreen.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class ContinuousExecuteUseCase<in REQUEST, out RESULT> constructor(
    private val coroutineContextProvider: CoroutineContextProvider
) : BaseUseCase<REQUEST, RESULT>() {
    final override suspend fun execute(value: REQUEST, callback: (RESULT) -> Unit) {
        withContext(coroutineContextProvider.io) {
            executeInBackground(value) { result ->
                CoroutineScope(coroutineContextProvider.main).launch {
                    callback(result)
                }
            }
        }
    }

    abstract suspend fun executeInBackground(
        request: REQUEST,
        callback: (RESULT) -> Unit
    )

    open fun cleanup() {}
}
