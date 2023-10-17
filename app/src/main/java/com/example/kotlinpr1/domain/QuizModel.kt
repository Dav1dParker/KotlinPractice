package com.example.kotlinpr1.domain

data class QuizModel(
    val category: String? = null,
    val type: String? = null,
    val difficulty: String? = null,
    val question: String? = null,
    val correct_answer: String? = null,
    val incorrect_answers: List<String>? = null
) {
}

