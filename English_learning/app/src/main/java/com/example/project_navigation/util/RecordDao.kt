package com.example.project_navigation.util

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.project_navigation.entity.Record

@Dao
interface RecordDao{
    @Insert
    fun insert(record: Record)
    @Query("SELECT * FROM Record")
    fun getRecord(): LiveData<List<Record>>

    @Query("DELETE  FROM Record")
    fun deletAllRecord()
}