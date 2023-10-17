package com.example.kotlinpr1.domain

import android.database.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    //Describe the request type and the relative URL//
    @GET("api.php?amount=10")
    suspend fun getData(): Response<ResponseBody>
}