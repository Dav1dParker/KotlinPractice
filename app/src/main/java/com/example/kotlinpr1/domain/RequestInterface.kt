package com.example.kotlinpr1.domain

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestInteface {
    @GET("api/questions?")
    fun getJson(
        @Query("categories") key: String,
        @Query("limit") key1: String,
        @Query("difficulty") key2: String
    ): Call<List<QuizModel>>
}