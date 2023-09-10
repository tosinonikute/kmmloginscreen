package com.kmmloginscreen.domain.base

import com.kmmloginscreen.domain.base.DomainException

data class UnknownDomainException(override val throwable: Throwable) : DomainException(throwable) {
    constructor(errorMessage: String) : this(Throwable(errorMessage))
}
