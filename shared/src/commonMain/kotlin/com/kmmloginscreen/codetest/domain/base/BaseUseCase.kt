package com.kmmloginscreen.domain.base

abstract class BaseUseCase<in REQUEST, out RESULT> {
    abstract suspend fun execute(value: REQUEST, callback: (RESULT) -> Unit)

    fun onError(throwable: Throwable): DomainException = UnknownDomainException(throwable)
}
