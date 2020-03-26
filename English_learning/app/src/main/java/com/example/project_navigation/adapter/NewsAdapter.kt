package com.example.project_navigation.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.example.project_navigation.R
import com.example.project_navigation.WebActivity
import com.example.project_navigation.entity.News
/**
 *    author : 徐浩航
 *    version: 1.0
 */
class NewsAdapter(val data:ArrayList<News>):BaseAdapter() {
    inner class ViewHolder(
        var itemIcon: ImageView,
        var itemName: TextView,
        var itemNo:TextView
    )

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView:View

        val holder: ViewHolder
        if(convertView==null   ) {
            itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_news, parent, false)
            val itemIcon = itemView.findViewById<ImageView>(R.id.itemIcon)
            val itemName = itemView.findViewById<TextView>(R.id.title)
            val itemNo = itemView.findViewById<TextView>(R.id.src)
            holder = ViewHolder(itemIcon, itemName, itemNo)
            itemView.tag = holder
        }else{
            itemView=convertView
            holder=itemView.tag as ViewHolder

        }
        val s: News =data[position]
        holder.itemIcon.setImageResource(s.icon)
        holder.itemName.text=s.title
        holder.itemNo.text=s.src

        return itemView
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return data.size
    }
}


