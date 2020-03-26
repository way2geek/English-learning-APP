package com.example.project_navigation.viewModel

import androidx.lifecycle.ViewModel
import com.example.project_navigation.repository.WordRepository
import com.example.project_navigation.entity.Word
import androidx.lifecycle.LiveData
import android.content.Context
/**
 *    author : 徐浩航
 *    version: 1.0
 */

class WordModel: ViewModel(){
    private var wordRepository= WordRepository()
    fun WordModel(context: Context){
        wordRepository.WordRepository(context.applicationContext)
    }
    fun getAllWordsLive(): LiveData<List<Word>> {
        return wordRepository.getAllWordsLive()
    }
    fun getWord(wd:String):LiveData<List<Word>>{
        return wordRepository.getWords(wd)
    }
}