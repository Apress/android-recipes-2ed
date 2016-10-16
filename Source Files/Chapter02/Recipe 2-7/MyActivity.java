package com.examples.rotation;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Calling super is required
        super.onCreate(savedInstanceState);
        //Load view resources
        loadView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //Calling super is required
        super.onConfigurationChanged(newConfig);
        //Store important UI state
        saveState();
        //Reload the view resources
        loadView();
    }

    private void saveState() {
        //Implement any code to persist the UI state
    }

    private void loadView() {
        setContentView(R.layout.main);

        //Handle any other required UI changes upon a new configuration
        //Including restoring and stored state
    }
}
