package com.example.kotlinpr1.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpr1.data.repositories.DataRepository

class MainActivityViewModel : ViewModel() {
    private val dataRepository = DataRepository()
    val data: LiveData<String> = dataRepository.getData()
}