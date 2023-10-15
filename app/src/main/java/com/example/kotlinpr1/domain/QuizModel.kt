package com.example.kotlinpr1.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuizModel(
    @SerializedName("category")
    @Expose
    val category: String,
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("correctAnswer")
    @Expose
    val correctAnswer: String,
    @SerializedName("incorrectAnswers")
    @Expose
    val incorrectAnswers: List<String>? = null,
    @SerializedName("question")
    @Expose
    val question: String,
    @SerializedName("tags")
    @Expose
    val tags: List<String>? = null,
    @SerializedName("type")
    @Expose
    val type: String,
    @SerializedName("difficulty")
    @Expose
    val difficulty: String,
    @SerializedName("regions")
    @Expose
    val regions: List<Any>? = null
)