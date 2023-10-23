package com.example.kotlinpr1.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinpr1.data.repositories.QuestionsEntity
import com.example.kotlinpr1.data.repositories.QuizDataBase
import com.example.kotlinpr1.data.repositories.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    val getAll: LiveData<List<QuestionsEntity>>
    private val repository: QuizRepository

    init {
        val quizDao = QuizDataBase.getDataBase(application).QuestionDao()
        repository = QuizRepository(quizDao)
        getAll = repository.getAll
    }

    fun insertQuestions(quiz: QuestionsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertquiz(quiz)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}