package com.example.project_navigation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.UpdateListener
import com.example.project_navigation.util.Callback
import com.example.project_navigation.entity.Collection
import com.example.project_navigation.event.LoginEvent
import com.example.project_navigation.event.RxBus
import com.example.project_navigation.event.registerInBus


class CollectAdapter(var list: MutableList<Collection>,context: Context): RecyclerView.Adapter<CollectAdapter.ViewHolder>() {

    private  var contex=context
    var itemView: View? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        itemView=layoutInflater.inflate(com.example.project_navigation.R.layout.item_collection, parent, false)
        return ViewHolder(itemView!!)
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val word = list[position]
        holder.wd.text=word.word
        holder.ch.text=word.meaning
        holder.del.setOnClickListener(){
            var un:String=""
            RxBus.observe<LoginEvent>().subscribe(){ t: LoginEvent?->kotlin.run {un="${t?.user?.username}"}} .registerInBus(this)
            val coll=Collection(un,holder.wd.text.toString(),holder.ch.text.toString())
            getObjectId(un,holder.wd.text.toString(),object : Callback {
                override fun getId(id:String) {
                    coll.objectId=id
                    coll.delete(object :UpdateListener(){
                        override fun done(ex: BmobException?) {
                            if (ex == null) {
                                Toast.makeText(contex, "删除成功", Toast.LENGTH_LONG).show()
                                list.removeAt(position)
                                notifyDataSetChanged()

                            } else {
                                Toast.makeText(contex, ex.message, Toast.LENGTH_LONG).show()
                            }
                        }
                    })
                }
            })

        }
    }
    fun getObjectId(user:String,wd:String,callback: Callback){
        var obId:String=""
        var bmobQuery: BmobQuery<Collection> = BmobQuery()
        bmobQuery.addWhereEqualTo("username", user)
        bmobQuery.addWhereEqualTo("word", wd)
        bmobQuery.addQueryKeys("objectId")
        bmobQuery.findObjects(object : FindListener<Collection>(){
            override fun done(Collection: MutableList<Collection>?, ex: BmobException?) {
                if (ex == null) {
                    Log.i("objidd",Collection!![0].objectId)
                    obId= Collection!![0].objectId
                    callback.getId(obId)
                } else {

                }
            }
        })
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wd=itemView.findViewById<TextView>(com.example.project_navigation.R.id.rtitle)
        val ch=itemView.findViewById<TextView>(com.example.project_navigation.R.id.rdate)
        val del=itemView.findViewById<ImageButton>(com.example.project_navigation.R.id.del)
    }
}