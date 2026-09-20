package com.provital.sst;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

/** Muestra las vistas JSP existentes conservando la sesión creada por la API. */
public class WebModuloActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "com.provital.sst.EXTRA_URL";
    public static final String EXTRA_COOKIE = "com.provital.sst.EXTRA_COOKIE";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_modulo);

        String url = getIntent().getStringExtra(EXTRA_URL);
        String cookie = getIntent().getStringExtra(EXTRA_COOKIE);
        if (url == null || cookie == null) {
            finish();
            return;
        }

        CookieManager gestorCookies = CookieManager.getInstance();
        gestorCookies.setAcceptCookie(true);
        gestorCookies.setCookie(url, cookie);
        gestorCookies.flush();

        WebView webView = findViewById(R.id.webModulo);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        WebView webView = findViewById(R.id.webModulo);
        if (webView.canGoBack()) webView.goBack(); else super.onBackPressed();
    }
}
