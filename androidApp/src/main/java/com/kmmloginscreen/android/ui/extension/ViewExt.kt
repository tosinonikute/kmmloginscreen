package com.kmmloginscreen.android.ui.extension

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat

fun EditText.addActionOnTextChanged(action: (String?) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) = action(s.toString())
    })
}

fun View.getColorCompat(resourceId: Int) = this.context.getColorCompat(resourceId)

fun Context.getColorCompat(resourceId: Int) = ContextCompat.getColor(this, resourceId)
