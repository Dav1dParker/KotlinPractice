package com.example.kotlinpr1.domain

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {
    //Describe the request type and the relative URL//
    @GET("api.php?amount=1&type=multiple")
    suspend fun getData(): Response<ResponseBody>
}