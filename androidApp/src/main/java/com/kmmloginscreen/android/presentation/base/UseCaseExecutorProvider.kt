package com.kmmloginscreen.android.presentation.base

import com.kmmloginscreen.domain.base.UseCaseExecutor
import kotlinx.coroutines.CoroutineScope

typealias UseCaseExecutorProvider = @JvmSuppressWildcards (coroutineScope: CoroutineScope) -> UseCaseExecutor
