package com.examples.handleurl;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class MyActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        //Add a client to the view
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://www.google.com");
        setContentView(webview);
    }
}
