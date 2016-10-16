package com.examples.rest;

import java.io.StringReader;
import java.net.URI;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.client.methods.HttpGet;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.examples.rest.RSSHandler.NewsItem;
import com.examples.rest.RestTask.ResponseCallback;

public class FeedActivity extends Activity implements ResponseCallback {
    private static final String TAG = "FeedReader";
	private static final String FEED_URI = "http://news.google.com/?output=rss";
	
	private ListView mList;
	private ArrayAdapter<NewsItem> mAdapter;
	private ProgressDialog mProgress;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mList = new ListView(this);
        mAdapter = new ArrayAdapter<NewsItem>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                NewsItem item = mAdapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(item.link));
                startActivity(intent);
            }
        });
        
        setContentView(mList);
    }
	
    @Override
    public void onResume() {
        super.onResume();
    	//Retrieve the RSS feed
        try{
            HttpGet feedRequest = new HttpGet( new URI(FEED_URI) );
            RestTask task = new RestTask();
            task.setResponseCallback(this);
            task.execute(feedRequest);
            mProgress = ProgressDialog.show(this, "Searching", "Waiting For Results...", true);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }
    
    @Override
    public void onRequestSuccess(String response) {
        if (mProgress != null) {
            mProgress.dismiss();
            mProgress = null;
        }
        //Process the response data
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser p = factory.newSAXParser();
            RSSHandler parser = new RSSHandler();
            p.parse(new InputSource(new StringReader(response)), parser);
            
            mAdapter.clear();
            for(NewsItem item : parser.getParsedItems()) {
                mAdapter.add(item);
            }
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }
    
    @Override
    public void onRequestError(Exception error) {
        if (mProgress != null) {
            mProgress.dismiss();
            mProgress = null;
        }
        //Display the error
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}