package com.kmmloginscreen.domain.base

data class UnknownDomainException(override val throwable: Throwable) : DomainException(throwable) {
    constructor(errorMessage: String) : this(Throwable(errorMessage))
}
