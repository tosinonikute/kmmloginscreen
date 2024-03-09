package com.kmmloginscreen.android.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmmloginscreen.domain.base.ErrorLogger
import com.kmmloginscreen.domain.base.UseCaseExecutor
import kotlinx.coroutines.cancel

abstract class BaseViewModel<VIEW_STATE : ViewState>(
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val errorLogger: ErrorLogger
) : ViewModel() {

    constructor(useCaseExecutorProvider: UseCaseExecutorProvider) : this(
        useCaseExecutorProvider,
        DebugErrorLogger()
    )

    private val _viewState = MutableLiveData<VIEW_STATE>().apply { value = initialState() }
    val viewState: LiveData<VIEW_STATE> get() = _viewState

    val useCaseExecutor: UseCaseExecutor = useCaseExecutorProvider(viewModelScope)

    fun updateState(newViewState: VIEW_STATE) {
        _viewState.value = newViewState
    }

    fun currentViewState() = viewState.value ?: initialState()

    abstract fun initialState(): VIEW_STATE

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel("Scope cleared")
    }
}
