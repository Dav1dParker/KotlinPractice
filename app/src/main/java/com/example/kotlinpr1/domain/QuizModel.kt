package com.example.kotlinpr1.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuizModel(
    var category: String? = null,
    var type: String? = null,
    var difficulty: String? = null,
    var question: String? = null,
    var correct_answer: String? = null,
    var incorrect_answers: ArrayList<String>? = null
)