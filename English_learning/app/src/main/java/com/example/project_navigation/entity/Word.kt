package com.example.project_navigation.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WordInfo")
data class Word (
    @PrimaryKey
    var id: Int ,
    var word:String?=null,
    var meaning: String?=null,
    var sentence: String?=null
)
