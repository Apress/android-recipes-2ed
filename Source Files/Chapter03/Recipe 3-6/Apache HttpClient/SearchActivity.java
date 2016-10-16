package com.examples.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.examples.rest.RestTask.ResponseCallback;

public class SearchActivity extends Activity implements ResponseCallback {

    private static final String SEARCH_URI = "https://www.googleapis.com/customsearch/v1?key=%s&cx=%s&q=%s";
    private static final String SEARCH_KEY = "AIzaSyBbW-W1SHCK4eW0kK74VGMLJj_b-byNzkI";
    private static final String SEARCH_CX = "008212991319514020231:1mkouq8yagw";
    private static final String SEARCH_QUERY = "Android";
    
    private static final String POST_URI = "http://httpbin.org/post";

	private TextView mResult;
	private ProgressDialog mProgress;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = new ScrollView(this);
        mResult = new TextView(this);
        scrollView.addView(mResult, new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        setContentView(scrollView);
        
        try{
            //Simple GET
            String url = String.format(SEARCH_URI, SEARCH_KEY, SEARCH_CX, SEARCH_QUERY);
            HttpGet searchRequest = new HttpGet(url);
            
            //Simple POST            
            HttpPost postRequest = new HttpPost( new URI(POST_URI) );
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("title", "Android Recipes"));
            parameters.add(new BasicNameValuePair("summary", "Learn Android Quickly"));
            parameters.add(new BasicNameValuePair("authors", "Smith/Friesen"));
            postRequest.setEntity(new UrlEncodedFormEntity(parameters));
            
            RestTask task = new RestTask();
            task.setResponseCallback(this);
            task.execute(searchRequest);
            //Display progress to the user
            mProgress = ProgressDialog.show(this, "Searching", "Waiting For Results...", true);
        } catch (Exception e) {
            mResult.setText(e.getMessage());
        }
    }
    
	@Override
	public void onRequestSuccess(String response) {
        //Clear progress indicator
        if(mProgress != null) {
            mProgress.dismiss();
        }

        //Process the response data (here we just display it)
        mResult.setText(response);
	}
	
	@Override
	public void onRequestError(Exception error) {
        //Clear progress indicator
        if(mProgress != null) {
            mProgress.dismiss();
        }

        //Process the response data (here we just display it)
        mResult.setText(error.getMessage());
	}
}