package com.infyapps.kotlin.model

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

class InputValidations(private val context: Context) {
    // method to check InputEditText filled
    fun isInputEditTextFilled(
        textInputEditText: EditText,
        textInputLayout: TextInputLayout,
        message: String
    ): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty()) {
            textInputLayout.error = message
            textInputEditText.requestFocus()
            hideKeyboardFrom(textInputEditText)
            return false
        } else {
            textInputLayout.isErrorEnabled = false
        }

        return true
    }

    // method to check InputEditText has valid email .
    fun isInputEditTextEmail(
        textInputEditText: EditText,
        textInputLayout: TextInputLayout,
        message: String
    ): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputLayout.error = message
            textInputEditText.requestFocus()
            hideKeyboardFrom(textInputEditText)
            return false
        } else {
            textInputLayout.isErrorEnabled = false
        }
        return true
    }


    // method to check InputEditText has valid number .
    fun isInputEditTextNumber(
        textInputEditText: EditText,
        textInputLayout: TextInputLayout,
        message: String
    ): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty() || value.length != 10) {
            textInputLayout.error = message
            textInputEditText.requestFocus()
            hideKeyboardFrom(textInputEditText)
            return false
        } else {
            textInputLayout.isErrorEnabled = false
        }
        return true
    }


    //method to Hide keyboard
    private fun hideKeyboardFrom(view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            view.windowToken,
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )
    }
}