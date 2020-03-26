package com.example.project_navigation

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_navigation.adapter.RecordAdapter
import com.example.project_navigation.entity.Record
import com.example.project_navigation.util.RecordDataBaseHelper
import com.example.project_navigation.viewModel.RecordModel
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_dict.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_record.*
import kotlin.concurrent.thread

class RecordActivity : AppCompatActivity() {

    lateinit var recordViewModel:RecordModel
    lateinit var record: LiveData<List<Record>>
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        StatusBarUtil.setTranslucent(this,0)
        val layoutManager = LinearLayoutManager(this)
        var recordadapter=RecordAdapter(applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView2.layoutManager=layoutManager
        recyclerView2.adapter=recordadapter
        recordViewModel= ViewModelProviders.of(this).get(RecordModel::class.java)
        record= recordViewModel.getAllRecordsLive(this)
        record.observe(this, Observer {
            record->
             recordadapter.setAllReords(record)
             recordadapter.notifyDataSetChanged()
        })
       clear.setOnClickListener(){
           var del =MyAsyncTask()
           del.execute()
       }
    }
    inner class MyAsyncTask: AsyncTask<Void,Void,String>(){
        override fun doInBackground(vararg params: Void?): String {
            recordViewModel.deleteRecord(this@RecordActivity)
            return "OK"
        }
    }
}
