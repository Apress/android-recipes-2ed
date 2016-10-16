package com.examples.javascript;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MyActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(mClient);
        //Attach the custom interface to the view
        webview.addJavascriptInterface(new MyJavaScriptInterface(), "BRIDGE");

        setContentView(webview);
        
        webview.loadUrl("file:///android_asset/form.html");
    }

    private static final String JS_SETELEMENT = "javascript:document.getElementById('%s').value='%s'";
    private static final String JS_GETELEMENT = 
                    "javascript:window.BRIDGE.storeElement('%s',document.getElementById('%s').value)";
    private static final String ELEMENTID = "emailAddress";

    private WebViewClient mClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //Before leaving the page, attempt to retrieve the email using JavaScript
            view.loadUrl(String.format(JS_GETELEMENT, ELEMENTID, ELEMENTID));
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //When page loads, inject address into page using JavaScript
            SharedPreferences prefs = getPreferences(Activity.MODE_PRIVATE);
            view.loadUrl(String.format(JS_SETELEMENT, ELEMENTID, prefs.getString(ELEMENTID, "")));
        }
    };

    private class MyJavaScriptInterface {
        //Store an element in preferences
        @SuppressWarnings("unused")
        public void storeElement(String id, String element) {
            SharedPreferences.Editor edit = getPreferences(Activity.MODE_PRIVATE).edit();
            edit.putString(id, element);
            edit.commit();
            //If element is valid, raise a Toast
            if(!TextUtils.isEmpty(element)) {
                Toast.makeText(MyActivity.this, element, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
