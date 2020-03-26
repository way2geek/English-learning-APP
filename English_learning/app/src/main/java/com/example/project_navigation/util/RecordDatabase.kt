package com.example.project_navigation.util

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.project_navigation.entity.Record


@Database(entities = [Record::class],version = 1)
abstract class RecordDatabase:RoomDatabase() {
    abstract fun getRecordDao():RecordDao
}