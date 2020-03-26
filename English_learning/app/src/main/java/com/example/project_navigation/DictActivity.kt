package com.example.project_navigation

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_navigation.adapter.WordAdapter

import com.example.project_navigation.viewModel.WordModel
import kotlinx.android.synthetic.main.activity_dict.*
import com.example.project_navigation.entity.Word
import com.jaeger.library.StatusBarUtil

/**
 *    author : 徐浩航
 *    version: 1.0
 */


class DictActivity : AppCompatActivity() {
    lateinit var wordViewModel: WordModel
    lateinit var filteredWords:LiveData<List<Word>>
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dict)
        StatusBarUtil.setTranslucent(this,22)
        val layoutManager = LinearLayoutManager(this)
        var MyAdapter=WordAdapter(applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=MyAdapter
        wordViewModel= ViewModelProviders.of(this).get(WordModel::class.java)
        wordViewModel.WordModel(applicationContext)
        filteredWords=wordViewModel.getAllWordsLive()
        filteredWords.observe(this, Observer
           { words ->
                MyAdapter.setAllWords(words)
                MyAdapter.notifyDataSetChanged()
            })
             wordSearch.maxWidth=(1000)
             wordSearch.onActionViewExpanded()
             wordSearch.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                var newText=newText!!.trim()
                filteredWords.removeObserver { this }
                filteredWords=wordViewModel.getWord(newText)
                filteredWords.observe(this@DictActivity, Observer
                { words ->
                    MyAdapter.setAllWords(words)
                    MyAdapter.notifyDataSetChanged()
                })
                return true
            }
        })
    }
}
