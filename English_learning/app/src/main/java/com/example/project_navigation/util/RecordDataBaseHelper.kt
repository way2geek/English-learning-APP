package com.example.project_navigation.util

import android.content.Context
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.project_navigation.entity.Record

class RecordDataBaseHelper constructor(context: Context) {
    val appDataBase = Room.databaseBuilder(context, RecordDatabase::class.java,"Record").build()!!
    companion object {
        @Volatile
        var INSTANCE: RecordDataBaseHelper? = null

        fun getInstance(context: Context): RecordDataBaseHelper {
            if (INSTANCE == null) {
                synchronized(RecordDataBaseHelper::class) {
                    if (INSTANCE == null) {
                        INSTANCE = RecordDataBaseHelper(context.applicationContext)
                    }
                }
            }
            return INSTANCE!!
        }
    }
    fun insertRecord(Record: Record) {
        appDataBase.getRecordDao().insert(Record)
    }
    fun getRecord(): LiveData<List<Record>> {
        return appDataBase.getRecordDao().getRecord()
    }
    fun deleteRecord(){
        return appDataBase.getRecordDao().deletAllRecord()
    }
}