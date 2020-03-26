package com.example.project_navigation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import cn.bmob.v3.Bmob
import com.example.project_navigation.adapter.CollectAdapter
import com.example.project_navigation.event.LoginEvent
import com.example.project_navigation.event.MutableListEvent
import com.example.project_navigation.event.RxBus
import com.example.project_navigation.event.registerInBus
import com.example.project_navigation.repository.CollectionRepository
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_word_collection.*

class WordCollectionActivity : AppCompatActivity() {
    var s= CollectionRepository()
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Bmob.initialize(this,"d1b43716cc00c524ae63b8ddad2b522e")
        setContentView(R.layout.activity_word_collection)
        StatusBarUtil.setTranslucent(this,22)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = VERTICAL
        var user:String=""
        RxBus.observe<LoginEvent>().subscribe(){t:LoginEvent?->kotlin.run { user="${t?.user?.username}"}} .registerInBus(this)
        s.getWords(applicationContext,user)
        crec.layoutManager=layoutManager
        RxBus.observe<MutableListEvent>().subscribe(){ t: MutableListEvent?->kotlin.run {crec.adapter=CollectAdapter( t!!.collect,applicationContext)}}.registerInBus(this)

    }
}
