package com.example.kotlinpr1.domain

import androidx.lifecycle.LiveData
import com.example.kotlinpr1.data.repositories.DataRepository

class GetDataUseCase(private val dataRepository: DataRepository) {
    fun execute(): LiveData<String> {
        return dataRepository.getData()
    }
}