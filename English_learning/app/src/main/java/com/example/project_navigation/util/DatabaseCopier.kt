package com.example.project_navigation.util

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import androidx.room.Room
import android.content.Context
import android.util.Log
/**
 *    author : 徐浩航
 *    version: 1.0
 */
class DatabaseCopier private  constructor(context: Context){
    val base:AppDatabase
    private var appContext: Context = context

    init {
        copyAttachedDatabase(appContext,DATABASE_NAME)
        base = Room.databaseBuilder(appContext.applicationContext, AppDatabase::class.java, DATABASE_NAME).addMigrations(AppDatabase.MIGRATION_1_2).build()
    }
    companion object{
        @Volatile
        var instance: DatabaseCopier? = null

        fun getInstance(context: Context): DatabaseCopier {
            if (instance == null) {
                synchronized(DatabaseCopier::class) {
                    if (instance == null) {
                        instance = DatabaseCopier(context)
                    }
                }
            }
            return instance!!
        }
        private val TAG = DatabaseCopier::class.java.simpleName
        private val DATABASE_NAME = "wordDetail.db"
    }


    private fun copyAttachedDatabase(context: Context, databaseName: String) {
        val dbPath = context.getDatabasePath(databaseName)
        if (dbPath.exists()) {
            return
        }
        dbPath.parentFile!!.mkdirs()
        try {
            FileOutputStream(context.getDatabasePath(databaseName)).use { out ->
                context.assets.open(databaseName).use {
                    it.copyTo(out)
                }
            }
        } catch (e: IOException) {
            Log.d(TAG, "Failed to open file", e)
            e.printStackTrace()
        }

    }
}






