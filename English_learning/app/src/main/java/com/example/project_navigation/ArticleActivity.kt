package com.example.project_navigation

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_navigation.adapter.ArticleAdapter
import com.example.project_navigation.entity.Article
import com.google.gson.Gson
import com.jaeger.library.StatusBarUtil
import java.net.URL
/**
 *    author : 徐浩航
 *    version: 1.0
 */
class ArticleActivity : AppCompatActivity() {

    data class ResultBean(var status: String,var totalResults: Int,var articles: ArrayList<Article>,var title: String)
    lateinit var mRecyclerView : RecyclerView
    lateinit var adapter: ArticleAdapter
    lateinit var linearManager: LinearLayoutManager
    var articleArray: ArrayList<Article> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        StatusBarUtil.setTranslucent(this,22)
        mRecyclerView = findViewById<RecyclerView>(R.id.article_recycler)
        adapter = ArticleAdapter(this)
        linearManager = LinearLayoutManager(this)
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = linearManager
        GetArticle().execute()

    }
    inner class GetArticle: AsyncTask<Void,Void,String >(){
        override fun doInBackground(vararg params: Void?): String{
            val jsonStr =URL("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=13ff0ba6e2bf4d34bc20efdd24e1a67c").readText()
            return  jsonStr
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)

            articleArray = Gson().fromJson(result, ResultBean::class.java).articles
            articleArray[0].title
            adapter.setData(articleArray)
        }
    }
}
