package com.example.kotlindemo.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlindemo.data.User

class LoginViewModel : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    fun setUsername(username: String) {
        Log.d("LoginViewModel", "Confirm update..$username")
        _username.value = username
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun saveUserData(user: User, context: Context) {
        val sharedPref = context.getSharedPreferences(
            "user_details", Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            putString("username", user.username)
            apply()
        }
    }

    fun authenticate(username: String, password: String, context: Context): Boolean {
        // Replace with your actual authentication logic
        setUsername(username)
        saveUserData(User(username), context)
        return username == "user" && password == "pass"
    }
}
