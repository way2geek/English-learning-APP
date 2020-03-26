package com.example.project_navigation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.example.project_navigation.LoginActivity
import com.example.project_navigation.R
import com.example.project_navigation.WebActivity
import com.example.project_navigation.entity.Collection
import com.example.project_navigation.entity.Word
import com.example.project_navigation.event.LoginEvent
import com.example.project_navigation.event.RxBus
import com.example.project_navigation.event.registerInBus

/**
 *    author : 徐浩航
 *    version: 1.0
 */

class WordAdapter(context:Context): RecyclerView.Adapter<WordAdapter.MyViewHolder>() {
    private  var allWords=ArrayList<Word>()
    private  var contex=context
    var itemView: View? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        itemView=layoutInflater.inflate(com.example.project_navigation.R.layout.item_word, parent, false)
        return MyViewHolder(itemView!!)
    }
    fun setAllWords(allWords: List<Word>) {

        this.allWords= allWords as ArrayList<Word>
    }
    override fun getItemCount(): Int {
        return this.allWords.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val word = allWords[position]
        holder.wd.setText(word.word)
        holder.ch.setText(word.meaning)
        holder.num.setText((position+1).toString())
        holder.sentence.setText(word.sentence)
        holder.itemView.setOnClickListener(){
            var Uri  = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + holder.wd.getText());
            val intents = Intent(contex, WebActivity::class.java).putExtra("url", Uri.toString())
            intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            contex.startActivity(intents)
        }
        holder.collect.setOnClickListener(){
            var un:String=""
            RxBus.observe<LoginEvent>().subscribe(){ t: LoginEvent?->kotlin.run {un="${t?.user?.username}"}} .registerInBus(this)
            if (un==""){
                     Toast.makeText(contex, "请先登录", Toast.LENGTH_LONG).show()
                      var intent:Intent  =Intent(contex,LoginActivity::class.java);
                      holder.collect.getContext().startActivity(intent)
                      return@setOnClickListener
            }
            var collection= Collection(un,holder.wd.text.toString(),holder.ch.text.toString())
            collection.save(object : SaveListener<String>(){
                override fun done(objectId: String?, ex: BmobException?) {
                    if (ex == null) {
                        Toast.makeText(contex, "收藏成功", Toast.LENGTH_LONG).show()
                        holder.collect.setImageResource(R.drawable.ic_star)
                    } else {

                        Toast.makeText(contex, "已收藏", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wd=itemView.findViewById<TextView>(com.example.project_navigation.R.id.word)
        val ch=itemView.findViewById<TextView>(com.example.project_navigation.R.id.meaning)
        val sentence=itemView.findViewById<TextView>(com.example.project_navigation.R.id.sentence)
        val num=itemView.findViewById<TextView>(com.example.project_navigation.R.id.textViewNumber)
        val collect=itemView.findViewById<ImageButton>(com.example.project_navigation.R.id.add)
    }
}