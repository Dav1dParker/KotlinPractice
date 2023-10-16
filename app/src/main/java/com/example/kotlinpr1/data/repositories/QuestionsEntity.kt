package com.example.kotlinpr1.data.repositories
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "QuestionsTable")
data class QuestionsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "Question")
    var Question: String,
    @ColumnInfo(name = "Answer1")
    var Answer1: String,
    @ColumnInfo(name = "Answer2")
    var Answer2: String,
    @ColumnInfo(name = "Answer3")
    var Answer3: String,
    @ColumnInfo(name = "Answer4")
    var Answer4: String
)