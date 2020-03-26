package com.example.project_navigation.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.project_navigation.R
import com.example.project_navigation.WebActivity
import com.example.project_navigation.entity.Article
import com.example.project_navigation.entity.Record
import com.example.project_navigation.util.RecordDataBaseHelper
import java.util.*
import kotlin.collections.ArrayList

/**
 *    author : 徐浩航
 *    version: 1.0
 */
class ArticleAdapter(context: Context): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    var mContext : Context
    init {
        mContext = context
    }
    fun setData(data : ArrayList<Article>) {
        mData = data
        notifyDataSetChanged()
    }
    var mData: ArrayList<Article> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_article, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
}

    override fun onBindViewHolder(holder: ArticleAdapter.ViewHolder, position: Int) {
        holder.title.setText(mData.get(position).title)
        holder.author.setText("作者："+mData.get(position).author)
        holder.date.setText(mData.get(position).publishedAt.substring(0,mData.get(position).publishedAt.length-1))
        holder.src.setText("来源："+mData.get(position).source.name)
        holder.itemView.setOnClickListener(){
            var Uri  = Uri.parse(mData.get(position).url)
              var tms = Calendar.getInstance()
              var s = tms.get(Calendar.YEAR).toString() + "-" + tms.get(Calendar.MONTH).toString() + "-" + tms.get(Calendar.DAY_OF_MONTH).toString() + " " + tms.get(Calendar.HOUR_OF_DAY).toString() + ":" + tms.get(Calendar.MINUTE).toString() +":" + tms.get(Calendar.SECOND).toString()
              var record = Record(0,mData.get(position).title,s,Uri.toString())
              Thread({
                  RecordDataBaseHelper.getInstance(this.mContext).insertRecord(record)
              }).start()
              val intents = Intent(mContext,WebActivity::class.java).putExtra("url",Uri.toString())
              mContext.startActivity(intents)
        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        val src:TextView
        val author:TextView
        val date:TextView

        init {
            title   = itemView.findViewById(R.id.title) as TextView
            src = itemView.findViewById(R.id.src) as TextView
            author=itemView.findViewById(R.id.author) as TextView
            date=itemView.findViewById(R.id.date) as TextView
        }
    }
}