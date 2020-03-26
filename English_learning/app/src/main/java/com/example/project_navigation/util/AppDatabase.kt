package com.example.project_navigation.util

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.project_navigation.entity.Word
/**
 *    author : 徐浩航
 *    version: 1.0
 */
@Database(entities = [Word::class],version = 2, exportSchema = false)
abstract  class AppDatabase:RoomDatabase() {
    abstract fun getWordDao(): WordDao
    companion object {
        @JvmField
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }
    }
}