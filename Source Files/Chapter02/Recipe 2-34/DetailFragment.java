package com.examples.fragmentsample;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailFragment extends Fragment {
    
    private WebView mWebView;
    
    /*
     * Custom client to enable progress visibility.  Adding a client also
     * sets the WebView to load all requests directly rather than handing them
     * off to the Browser.
     */
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            getActivity().setProgressBarIndeterminateVisibility(true);
        }
        
        public void onPageFinished(WebView view, String url) {
            getActivity().setProgressBarIndeterminateVisibility(false);
        }
    };
    /*
     * Create and set up a basic WebView for the display of this Fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mWebView = new WebView(getActivity());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(mWebViewClient);
        
        return mWebView;
    }
    
    /*
     * External method to load a new site into the view
     */
    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

}
