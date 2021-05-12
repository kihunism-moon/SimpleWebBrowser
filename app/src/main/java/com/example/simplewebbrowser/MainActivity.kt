package com.example.simplewebbrowser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {
// commit point
    private val goHomeButton: ImageButton by lazy {
        findViewById<ImageButton>(R.id.goHomeButton)
    }

    private val goBackButton: ImageButton by lazy {
        findViewById<ImageButton>(R.id.goBackButton)
    }

    private val goForwardButton: ImageButton by lazy {
        findViewById<ImageButton>(R.id.goForwardButton)
    }

    private val addressBar: EditText by lazy {
        findViewById<EditText>(R.id.addressBar)
    }

    private val webView: WebView by lazy {
        findViewById<WebView>(R.id.webView)
    }

    private val refreshLayout: SwipeRefreshLayout by lazy {
        findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        bindView()
    }

    override fun onBackPressed() {
        if(webView.canGoBack()) {
//            뒤로 갈 수 있다면
            webView.goBack()
        }else{
            super.onBackPressed()
        }
        super.onBackPressed()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initViews() {
        webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            webView.loadUrl(DEFAULT_URL)
        }

//            대부분 https의 프로토콜을 쓴다. -> http는 보안상의 문제가 있다.
//        안드로이드9버전 부터는 http를 지원하지 않는다.
//        http를 사용하려면 Manifest 수정

        /*
        private fun initView() {
            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.loadUrl("http://www.google.com")
        } -> 55 ~ 58 코드와 동일 (위에 코드가 좀 더 코틀린 다운 코드)
        */
    }

    private fun bindView() {

        goHomeButton.setOnClickListener {
            webView.loadUrl(DEFAULT_URL)
        }

        addressBar.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                webView.loadUrl(v.text.toString())
            }
            return@setOnEditorActionListener false
        }

        goBackButton.setOnClickListener {
            webView.goBack()
        }

        goForwardButton.setOnClickListener {
            webView.goForward()
        }

        refreshLayout.setOnRefreshListener {
            webView.reload()
        }
    }

    inner class WebViewClient: android.webkit.WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            refreshLayout.isRefreshing = false
        }
    }

    companion object{
        private const val DEFAULT_URL = "http://www.google.com"
        private const val NAVER_URL = "http://www.naver.com"
    }

}