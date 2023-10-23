package com.example.kotlinpr1.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpr1.data.repositories.DataRepository

class MainActivityViewModel : ViewModel() {
    private val dataRepository = DataRepository()
    val data: LiveData<String> = dataRepository.getData()


    private var orderArray = listOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    private var counter: Int = 0
    private var pointsCounter: Int = 0
    fun genNewOrder() {
        orderArray = orderArray.shuffled()
    }

    fun getOrder(): List<Int> {
        return orderArray
    }

    fun clearCounter() {
        counter = 0
    }

    fun countPlusPlus() {
        counter++
    }

    fun getCounter(): Int {
        return counter
    }

    fun clearPointsCounter() {
        this.pointsCounter = 0
    }

    fun pointsCounterPlusPlus() {
        this.pointsCounter++
    }

    fun getPointsCounter(): Int {
        return pointsCounter
    }
}