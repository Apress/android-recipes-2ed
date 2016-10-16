package com.examples.intercepturl;

import android.app.Activity;
import android.os.Bundle;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MyActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        //Add a client to the view
        webview.setWebViewClient(mClient);
        webview.loadUrl("http://www.google.com");
        setContentView(webview);
    }

    private WebViewClient mClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Uri request = Uri.parse(url);

            if(TextUtils.equals(request.getAuthority(), "www.google.com")) {
                //Allow the load
                return false;
            }

            Toast.makeText(MyActivity.this, "Sorry, buddy", Toast.LENGTH_SHORT).show();
            return true;
        }
    };
}
