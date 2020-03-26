package com.example.project_navigation.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.project_navigation.entity.Record
import com.example.project_navigation.util.RecordDataBaseHelper

class RecordModel:ViewModel() {

    fun getAllRecordsLive(Context: Context): LiveData<List<Record>> {
        return RecordDataBaseHelper.getInstance(Context).getRecord()
    }
    fun deleteRecord(Context: Context){
        return RecordDataBaseHelper.getInstance(Context).deleteRecord()
    }
}