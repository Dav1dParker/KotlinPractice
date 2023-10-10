package com.example.kotlinpr1.data.repositories

import androidx.lifecycle.LiveData

interface DataRemoteDataSource {
    fun getDataFromNetwork(): LiveData<String>
}