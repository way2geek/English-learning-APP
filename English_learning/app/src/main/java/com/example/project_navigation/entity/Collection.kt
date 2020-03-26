package com.example.project_navigation.entity

import android.widget.Toast
import cn.bmob.v3.BmobObject
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener

data class Collection(var username:String,var word:String,var meaning:String): BmobObject(){
}