package com.examples.localweb;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class MyActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        WebView upperView = (WebView)findViewById(R.id.upperview);
        //Zoom feature must be enabled
        upperView.getSettings().setBuiltInZoomControls(true);
        upperView.loadUrl("file:///android_asset/android.jpg");

        WebView lowerView = (WebView)findViewById(R.id.lowerview);
        String htmlString =
            "<h1>Header</h1><p>This is HTML text<br /><i>Formatted in italics</i></p>";
        lowerView.loadData(htmlString, "text/html", "utf-8");
    }
}
