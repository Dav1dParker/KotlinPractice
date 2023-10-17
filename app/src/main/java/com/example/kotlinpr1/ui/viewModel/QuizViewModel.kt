package com.example.kotlinpr1.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinpr1.data.repositories.QuestionsEntity
import com.example.kotlinpr1.data.repositories.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    application: Application,
    private val repository: QuizRepository,
    var getAll: LiveData<List<QuestionsEntity>>
) : AndroidViewModel(application) {


    init {
        getAll = repository.getAll
    }

    fun insertQuestions(weather: QuestionsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertWeather(weather)
        }
    }

    /*    fun deleteAll(){
            viewModelScope.launch(Dispatchers.IO){
                repository.deleteAll()
            }
        }*/
}