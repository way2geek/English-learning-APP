package com.example.project_navigation.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="Record")
data class Record (
    @PrimaryKey(autoGenerate = true)
    var rID:Int,
    @ColumnInfo(name = "title")
    var rTitle:String,
    @ColumnInfo(name = "date")
    var rDate:String,
    @ColumnInfo(name="url")
    var rUrl:String
)