package com.kmmloginscreen.domain.base

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class UseCaseExecutor(
    private var coroutineScope: CoroutineScope,
    private val errorLogger: ErrorLogger
) {
    open fun <IN_TYPE, OUT_TYPE> execute(
        useCase: BaseUseCase<IN_TYPE, OUT_TYPE>,
        value: IN_TYPE,
        callback: (OUT_TYPE) -> Unit = {},
        onError: (DomainException) -> Unit = {}
    ): Cancellable {
        val job = coroutineScope.launch {
            try {
                useCase.execute(value, callback)
            } catch (cancellationException: CancellationException) {
                errorLogger.log(cancellationException.cause ?: cancellationException)
            } catch (throwable: Throwable) {
                errorLogger.log(throwable)
                onError(
                    if (throwable is DomainException) {
                        throwable
                    } else {
                        useCase.onError(throwable)
                    }
                )
            }
        }
        return CancellableUseCase(job)
    }

    private class CancellableUseCase(private val job: Job) : Cancellable {
        override fun cancel() {
            job.cancel()
        }
    }
}
