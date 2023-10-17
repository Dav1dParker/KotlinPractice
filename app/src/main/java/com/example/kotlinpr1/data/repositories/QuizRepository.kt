package com.example.kotlinpr1.data.repositories

import androidx.lifecycle.LiveData
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class QuizRepository @Inject constructor(private val QuestionDao: QuestionDao) {
    val getAll: LiveData<List<QuestionsEntity>> = QuestionDao.getAll()

    suspend fun insertWeather(weather: QuestionsEntity) {
        QuestionDao.insertWeather(weather)
    }

    /*suspend fun deleteAll(){
        QuestionDao.deleteAll()
    }*/

    fun insertQuestions(questionsEntity: QuestionsEntity) {
        QuestionDao.insertWeather(questionsEntity)
    }
}