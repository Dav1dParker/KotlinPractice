package com.example.kotlinpr1.data.repositories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuestionsEntity::class], version = 1, exportSchema = false)
abstract class QuizDataBase : RoomDatabase() {

    abstract fun QuestionDao(): QuestionDao

    companion object {
        private var INSTANCE: QuizDataBase? = null

        fun getDataBase(context: Context): QuizDataBase {
            val instance = INSTANCE
            if (instance != null) return instance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDataBase::class.java,
                    "Questions.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}