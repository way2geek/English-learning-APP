package com.example.project_navigation.util

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.project_navigation.entity.Word
/**
 *    author : 徐浩航
 *    version: 1.0
 */
@Dao
interface WordDao{
    @Query("SELECT * FROM WordInfo Where word like:Word")
    fun getWords(Word:String):LiveData<List<Word>>

    @Query("SELECT * FROM WordInfo")
    fun getAllWords():LiveData<List<Word>>

}