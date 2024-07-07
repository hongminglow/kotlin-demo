package com.example.kotlindemo.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatButton

class LoginButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    init {
        // Initialize your custom button here (e.g., set background, padding, etc.)
        setupButton(attrs)
    }

    private fun setupButton(attrs: AttributeSet? = null ) {
        // Example setup
        setBackgroundResource(android.R.color.holo_purple)
        setTextColor(resources.getColor(android.R.color.white))
    }
}
