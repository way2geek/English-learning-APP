package com.example.project_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_web.*
import kotlin.math.log

/**
 *    author : 徐浩航
 *    version: 1.0
 */
class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        StatusBarUtil.setTranslucent(this,22)
        var s = findViewById<WebView>(R.id.webview)
        s.setWebViewClient(WebViewClient()
        )
        s.settings.setLoadWithOverviewMode(true);
        s.settings.setBuiltInZoomControls(true);
        s.settings.setJavaScriptEnabled(true)
        s.settings.setUseWideViewPort(true);
        s.settings.setSupportZoom(true);
        s.settings.setJavaScriptCanOpenWindowsAutomatically(true);
        s.settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        s.settings.setGeolocationEnabled(true);
        s.settings.setDomStorageEnabled(true);
        s.settings.setDatabaseEnabled(true);
        s.settings.setUseWideViewPort(true); // 关键点
        s.settings.setAllowFileAccess(true); // 允许访问文件
        s.settings.setSupportZoom(true); // 支持缩放
        s.settings.setLoadWithOverviewMode(true);
        s.settings.setPluginState(WebSettings.PluginState.ON);
        s.settings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容
        var url=intent.getStringExtra("url")
        if (url !=null&&url!="") {
            s.loadUrl(url)
        }
        var option=intent.getStringExtra("option")
        if (option !=null&&url!="") {
            when(option.toInt()){
                1 ->{
                    s.loadUrl("http://mov.bn.netease.com/open-movie/nos/mp4/2016/05/16/SBM8NN8G6_shd.mp4")
                }
                2->{
                    s.loadUrl("http://mov.bn.netease.com/open-movie/nos/mp4/2017/01/03/SC8U8K7BC_shd.mp4")
                }
                3->{
                    s.loadUrl("http://mov.bn.netease.com/open-movie/nos/mp4/2017/04/07/SCGGKRFJU_shd.mp4")
                }
                4->{
                    s.loadUrl("http://mov.bn.netease.com/open-movie/nos/mp4/2018/02/23/SDADQU6U9_shd.mp4")
                }
                5->{
                    s.loadUrl("http://m.kekenet.com/Article/15203/")
                }
        }
        }
    }
}
