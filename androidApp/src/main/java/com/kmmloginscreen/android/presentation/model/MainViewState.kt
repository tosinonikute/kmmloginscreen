package com.kmmloginscreen.android.presentation.model

import com.kmmloginscreen.android.presentation.base.ViewState

data class MainViewState(
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val emailValidationErrorMessage: String = "",
    val passwordValidationErrorMessage: String = "",
    val errorFailedString: String = ""
) : ViewState
