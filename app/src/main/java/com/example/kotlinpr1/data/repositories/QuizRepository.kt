package com.example.kotlinpr1.data.repositories

import androidx.lifecycle.LiveData

class QuizRepository(private val questionDao: QuestionDao) {
    val getAll: LiveData<List<QuestionsEntity>> = questionDao.getAll()

    suspend fun insertquiz(quiz: QuestionsEntity) {
        questionDao.insertquiz(quiz)
    }

    fun deleteAll() {
        questionDao.deleteAll()
    }

    fun insertQuestions(questionsEntity: QuestionsEntity) {
        questionDao.insertquiz(questionsEntity)
    }
}