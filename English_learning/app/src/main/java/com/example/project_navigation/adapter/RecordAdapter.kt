package com.example.project_navigation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_navigation.WebActivity
import com.example.project_navigation.entity.Record
import com.example.project_navigation.entity.Word

class RecordAdapter(context:Context) :RecyclerView.Adapter<RecordAdapter.MyViewHolder> (){
    private  var allRecords=ArrayList<Record>()
     private  var contex=context
     var itemView: View? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        itemView=layoutInflater.inflate(com.example.project_navigation.R.layout.item_record, parent, false)
        return RecordAdapter.MyViewHolder(itemView!!)
    }

    override fun getItemCount(): Int {
        return this.allRecords.size
    }
    fun setAllReords(allrecords: List<Record>) {

        this.allRecords= allrecords as ArrayList<Record>
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val record = allRecords[position]
        holder.title.setText(record.rTitle)
        holder.date.setText(record.rDate)
        holder.itemView.setOnClickListener(){
            val intents = Intent(this.contex, WebActivity::class.java).putExtra("url",record.rUrl)
            intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            contex.startActivity(intents)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title=itemView.findViewById<TextView>(com.example.project_navigation.R.id.rtitle)
        val date=itemView.findViewById<TextView>(com.example.project_navigation.R.id.rdate)
    }
}