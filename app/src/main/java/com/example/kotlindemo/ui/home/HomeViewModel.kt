package com.example.kotlindemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlindemo.data.User

class HomeViewModel()  : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragments"
    }
    private val _userLists = MutableLiveData<List<User>>()
    val userLists : LiveData<List<User>> = _userLists

    val text: LiveData<String> = _text

}