package com.example.kotlindemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            // Implement your authentication logic here
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (authenticate(username, password)) {
                // Authentication successful, navigate to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Prevent going back to login screen
            } else {
                // Show authentication failed message
            }
        }
    }

    private fun authenticate(username: String, password: String): Boolean {
        // Implement your authentication logic
        return username == "user" && password == "pass" // Example only, replace with real authentication
    }
}
