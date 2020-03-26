package com.example.project_navigation.repository

import android.content.Context
import android.os.AsyncTask
import com.example.project_navigation.entity.Word
import androidx.lifecycle.LiveData
import com.example.project_navigation.util.DatabaseCopier
import com.example.project_navigation.util.WordDao
/**
 *    author : 徐浩航
 *    version: 1.0
 */
class WordRepository {
    private var WordsLive: LiveData<List<Word>>? = null
    private var wordDao:WordDao?=null
    fun WordRepository(context: Context) {
        val wordDatabase = DatabaseCopier.getInstance(context.applicationContext)
        wordDao = wordDatabase.base.getWordDao()

    }
    fun getAllWordsLive(): LiveData<List<Word>> {
        this.WordsLive=wordDao!!.getAllWords()
        return WordsLive!!
    }
    fun getWords(wd:String):LiveData<List<Word>>{
        this.WordsLive = wordDao!!.getWords("%"+wd+"%")
        return WordsLive!!
    }
}
