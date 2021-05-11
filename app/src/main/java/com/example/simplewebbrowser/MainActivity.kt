package com.example.simplewebbrowser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private val addressBar: EditText by lazy {
        findViewById<EditText>(R.id.addressBar)
    }

    private val webView: WebView by lazy {
        findViewById<WebView>(R.id.webView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        bindView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initViews() {
        webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            webView.loadUrl("http://www.google.com")
        }

//            대부분 https의 프로토콜을 쓴다. -> http는 보안상의 문제가 있다.
//        안드로이드9버전 부터는 http를 지원하지 않는다.
//        http를 사용하려면 Manifest 수정

        /*

        private fun initView() {
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.loadUrl("http://www.google.com")
        }

        */

//        webView.webViewClient = WebViewClient()
//        webView.settings.javaScriptEnabled = true
//        webView.loadUrl("http://www.google.com")
    }

    private fun bindView() {
        addressBar.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                webView.loadUrl(v.text.toString())
            }
            return@setOnEditorActionListener false
        }
    }

}