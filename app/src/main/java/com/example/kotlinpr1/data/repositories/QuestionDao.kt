package com.example.kotlinpr1.data.repositories

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: QuestionsEntity)

    @Query("SELECT * FROM QuestionsTable")
    fun getAll(): LiveData<List<QuestionsEntity>>

    /*@Query("DELETE FROM QuestionsTable")
    suspend fun deleteAll()*/
}