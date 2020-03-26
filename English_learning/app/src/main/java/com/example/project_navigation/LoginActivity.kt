package com.example.project_navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import cn.bmob.v3.Bmob
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.example.project_navigation.entity.User
import com.example.project_navigation.evendt.LoginUser
import com.example.project_navigation.event.LoginEvent
import com.example.project_navigation.event.RxBus
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.logbtn
import java.util.*

/**
 *    author : 徐浩航
 *    version: 1.0
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var loginuser: LoginUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        StatusBarUtil.setTranslucentForImageView(this,255,imagebackground)
        var time = System.currentTimeMillis()
        var calendar = Calendar.getInstance()
        calendar.timeInMillis=time
        var background=findViewById<ImageView>(R.id.imagebackground)
        var apm=calendar.get(Calendar.AM_PM)
        if (apm==0){
            times.text="Morning"
            background.setImageResource(R.drawable.good_morning_img)
        }else{
            times.text="Night"
            background.setImageResource(R.drawable.good_night_img)
        }
        Bmob.initialize(this,"d1b43716cc00c524ae63b8ddad2b522e")
        logbtn.setOnClickListener(){
            var user = User()
            var username=textname.text.toString()
            var password=textpwd.text.toString()
            user.username = username
            user.setPassword(password)

            user.login(object : SaveListener<User>() {
                override fun done(currentUser: User?, ex: BmobException?) {
                    if (ex == null) {
                        Toast.makeText(this@LoginActivity, "登录成功", Toast.LENGTH_LONG).show()
                        loginuser=LoginUser(username)
                        RxBus.send(LoginEvent(loginuser))
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, ex.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
        signbtn.setOnClickListener(){
            var user = User()
            var username=textname.text.toString()
            var password=textpwd.text.toString()
            user.username = username
            user.setPassword(password)
            if(password.length>=6&&password.length<=16) {
                user.signUp(object : SaveListener<User>() {
                    override fun done(currentUser: User?, ex: BmobException?) {
                        if (ex == null) {
                            Toast.makeText(this@LoginActivity, "注册成功", Toast.LENGTH_LONG).show()
                            loginuser = LoginUser(username)
                            RxBus.send(LoginEvent(loginuser))
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, ex.message, Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }else{
                Toast.makeText(this@LoginActivity, "注册失败：请输入6~16位密码", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.unRegister(this)
    }
}
