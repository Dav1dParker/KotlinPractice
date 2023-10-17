package com.example.kotlinpr1.domain

import com.google.gson.annotations.SerializedName

data class QuizResponse(
    @SerializedName("response_code")
    val responseCode: Int = 0,
    val results: List<QuizModel>? = null
)