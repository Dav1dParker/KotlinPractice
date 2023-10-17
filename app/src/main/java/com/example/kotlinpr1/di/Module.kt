package com.example.kotlinpr1.di

import android.app.Application
import androidx.room.Room
import com.example.kotlinpr1.data.repositories.QuizDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideDateBase(application: Application) =
        Room.databaseBuilder(application, QuizDataBase::class.java, "Questions.db").build()

    @Provides
    fun provideQuestionDao(dataBase: QuizDataBase) = dataBase.QuestionDao()
}