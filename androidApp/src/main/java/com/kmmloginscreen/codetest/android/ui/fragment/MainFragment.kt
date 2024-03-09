package com.kmmloginscreen.android.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import com.kmmloginscreen.android.R
import com.kmmloginscreen.android.presentation.MainViewModel
import com.kmmloginscreen.android.presentation.model.MainViewState
import com.kmmloginscreen.android.ui.extension.addActionOnTextChanged
import com.kmmloginscreen.android.ui.extension.getColorCompat

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private val emailView: TextInputEditText get() = requireView().findViewById(R.id.email_input_view)
    private val passwordView: TextInputEditText get() = requireView().findViewById(R.id.password_input_view)
    private val saveButtonView: MaterialButton get() = requireView().findViewById(R.id.save_button_view)
    private val textInputEmailError: TextView get() = requireView().findViewById(R.id.text_input_email_error)
    private val textInputPasswordError: TextView get() = requireView().findViewById(R.id.text_input_password_error)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        viewModel.viewState.observe(viewLifecycleOwner, ::renderMainViewState)
    }

    private fun setClickListeners() {
        emailView.addActionOnTextChanged { text ->
            viewModel.onValidateEmail(text.toString())
        }
        passwordView.addActionOnTextChanged { text ->
            viewModel.onValidatePassword(text.toString())
        }
    }

    private fun renderMainViewState(viewState: MainViewState) {
        renderEmailView(viewState.isEmailValid, viewState.emailValidationErrorMessage)
        renderPasswordView(viewState.isPasswordValid, viewState.passwordValidationErrorMessage)
        renderSubmitView(viewState.isEmailValid && viewState.isPasswordValid)
        renderSnackBar(viewState.errorFailedString)
    }

    private fun renderEmailView(
        isValidPassword: Boolean,
        emailValidationErrorMessage: String
    ) {
        textInputEmailError.apply {
            isInvisible = isValidPassword
            text = emailValidationErrorMessage
        }
    }

    private fun renderPasswordView(
        isValidPassword: Boolean,
        passwordValidationErrorMessage: String
    ) {
        textInputPasswordError.apply {
            isInvisible = isValidPassword
            text = passwordValidationErrorMessage
        }
    }

    private fun renderSubmitView(isEnabled: Boolean) {
        val color = if (isEnabled) R.color.colorAccent else R.color.colorGrey
        saveButtonView.apply {
            setEnabled(isEnabled)
            setBackgroundColor(getColorCompat(color))
        }
    }

    private fun renderSnackBar(errorMessage: String) {
        if (errorMessage.isNotEmpty()) {
            snackBar(errorMessage)
        }
    }

    private fun snackBar(text: String) {
        val contentView = requireView().findViewById<View>(android.R.id.content)
        contentView?.let { view ->
            val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            snackBar.apply {
                setBackgroundTint(Color.RED)
                view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                    .setTextColor(Color.WHITE)
                show()
            }
        }
    }
}
