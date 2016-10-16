package com.examples.fragmentsample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;

import com.examples.fragmentsample.DataFragment.DataItem;

public class MainActivity extends FragmentActivity implements MasterFragment.OnItemSelectedListener {
    
    private MasterFragment mMaster;
    private DetailFragment mDetail;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Enable a progress indicator on the window
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.main);
        setProgressBarIndeterminateVisibility(false);
        
        // Load the data fragment.
        // If an instance does not exist in the FragmentManager attach a new one.
        DataFragment fragment = (DataFragment) getSupportFragmentManager().findFragmentByTag(DataFragment.TAG);
        if (fragment == null) {
            fragment = DataFragment.newInstance();
            // We want to retain this instance so we get the same one back on
            // configuration changes.
            fragment.setRetainInstance(true);
            //Attach the fragment with a tag rather than a container id
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(fragment, DataFragment.TAG);
            ft.commit();
        }
        
        // Get the details fragment
        mDetail = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_detail);
        
        // Either embed the master Fragment or hold onto it to show as a dialog
        mMaster = MasterFragment.newInstance();
        // If the container view exists, embed the Fragment
        View container = findViewById(R.id.fragment_master);
        if (container != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment_master, mMaster);
            ft.commit();
        }
    }

    @Override
    public void onDataItemSelected(DataItem selected) {
        //Pass the selected item to show in the detail view
        mDetail.loadUrl(selected.getUrl());
    }
    
    public void onShowClick(View v) {
        //When this button exists and is clicked, show the DetailFragment as a dialog
        mMaster.show(getSupportFragmentManager(), null);
    }
}
