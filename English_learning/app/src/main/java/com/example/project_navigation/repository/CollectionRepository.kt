package com.example.project_navigation.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.project_navigation.entity.Collection
import com.example.project_navigation.event.MutableListEvent
import com.example.project_navigation.event.RxBus
class CollectionRepository() {
    fun getWords(context: Context,user:String) {
        var bmobQuery: BmobQuery<Collection> = BmobQuery()
        if (user != "") {
            bmobQuery.addWhereEqualTo("username", user)
            bmobQuery.setLimit(1000)
            bmobQuery.findObjects(object : FindListener<Collection>() {
                override fun done(Collection: MutableList<Collection>?, ex: BmobException?) {
                    if (ex == null) {
                        Toast.makeText(context, "同步成功", Toast.LENGTH_LONG).show()

                        RxBus.send(MutableListEvent(Collection!!))

                    } else {
                        Toast.makeText(context, ex.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
        } else {
            Toast.makeText(context, "请先登录", Toast.LENGTH_LONG).show()
        }
    }
}

