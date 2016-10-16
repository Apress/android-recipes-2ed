package com.examples.fragmentsample;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class DataFragment extends Fragment {
    /*
     * This Fragment is an example of a Fragment that does not have a UI.
     * It exists solely to encapsulate the data logic for the application
     * in a way that is friendly for other Fragments to access.
     */
    
    public static final String TAG = "DataFragment";
    
    /*
     * Custom data model class to house our application's data
     */
    public static class DataItem {
        private String mName;
        private String mUrl;
        
        public DataItem(String name, String url) {
            mName = name;
            mUrl = url;
        }
        
        public String getName() {
            return mName;
        }
        
        public String getUrl() {
            return mUrl;
        }
    }
    
    /*
     * Factory method to create new Fragments
     */
    public static DataFragment newInstance() {
        return new DataFragment();
    }
    
    private ArrayList<DataItem> mDataSet;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Construct the initial data set
        mDataSet = new ArrayList<DataFragment.DataItem>();
        mDataSet.add(new DataItem("Google", "http://www.google.com"));
        mDataSet.add(new DataItem("Yahoo", "http://www.yahoo.com"));
        mDataSet.add(new DataItem("Bing", "http://www.bing.com"));
        mDataSet.add(new DataItem("Android", "http://www.android.com"));
    }
    
    //Accessor to serve the current data the application
    public ArrayList<DataItem> getLatestData() {
        return mDataSet;
    }
}
