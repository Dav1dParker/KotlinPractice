package com.example.kotlinpr1

import androidx.lifecycle.LiveData

interface DataRemoteDataSource {
    fun getDataFromNetwork(): LiveData<String>
}