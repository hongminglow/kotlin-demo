package com.example.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindemo.databinding.ActivityLoginBinding
import com.example.kotlindemo.ui.login.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var passwordEditText: EditText
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        passwordEditText = findViewById(R.id.password_edit_text)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        // Set default value to 8 asterisks
//        passwordEditText.setText("********")

        binding.loginButton.setOnClickListener {
            // Implement your authentication logic here
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (viewModel.authenticate(username, password, this)) {
                // Authentication successful, navigate to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
//                finish() // Prevent going back to login screen
            } else {
                // Show authentication failed message
            }
        }
    }

}
