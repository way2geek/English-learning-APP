package com.example.project_navigation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*


/**
 *    author : 徐浩航
 *    version: 1.0
 */
class MainActivity : AppCompatActivity() {

    override fun    onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController=findNavController(R.id.navhost)
        bottom_nav_view.setupWithNavController(navController)
        StatusBarUtil.setTranslucent(this,22)
    }
}

