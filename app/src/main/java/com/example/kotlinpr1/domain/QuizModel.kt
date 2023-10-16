package com.example.kotlinpr1.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuizModel(
    @SerializedName("category")
    @Expose
    val category: String,
    @SerializedName("type")
    @Expose
    val type: String,
    @SerializedName("difficulty")
    @Expose
    val difficulty: String,
    @SerializedName("question")
    @Expose
    val question: String,
    @SerializedName("correct_answer")
    @Expose
    val correctAnswer: String,
    @SerializedName("incorrect_answers")
    @Expose
    val incorrectAnswers: List<String>
)