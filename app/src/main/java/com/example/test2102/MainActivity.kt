package com.example.test2102

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener

class MainActivity : AppCompatActivity() {


    lateinit var myWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        myWebView = findViewById<WebView>(R.id.webView)
        myWebView.webViewClient = WebViewClient()
        myWebView.loadUrl("https://hq1.appsflyer.com/home")
        myWebView.settings.javaScriptEnabled = true

        //AppsFlyer Integration
        AppsFlyerLib.getInstance().init("Z7bAwgthtGfCY3EBXZaZRh", null, this)
        AppsFlyerLib.getInstance().start(this)
        AppsFlyerLib.getInstance().setDebugLog(true)

        AppsFlyerLib.getInstance().start(this, "Z7bAwgthtGfCY3EBXZaZRh", object :
            AppsFlyerRequestListener {
            override fun onSuccess() {
                Log.d("AppsFlyerSuperLog", "Launch sent successfully")
            }

            override fun onError(errorCode: Int, errorDesc: String) {
                Log.d("AppsFlyerSuperLog", "Launch failed to be sent:\n" +
                        "Error code: " + errorCode + "\n"
                        + "Error description: " + errorDesc)
            }
        })

        // log in with credentials

        myWebView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                myWebView.loadUrl("javascript:document.getElementsByName('username').value = 'username'");
                myWebView.loadUrl("javascript:document.getElementsByName('password').value = 'password'");
                myWebView.loadUrl("javascript:document.forms['login'].submit()");
            }
        })











    }

    override fun onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}