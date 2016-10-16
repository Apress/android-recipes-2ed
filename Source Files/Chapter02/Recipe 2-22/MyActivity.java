package com.examples.keyboard;

import android.app.Activity;
import android.os.Bundle;
import andorid.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MyActivity extends Activity implements OnEditorActionListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Add the listener to the views
        EditText text1 = (EditText)findViewById(R.id.text1);
        text1.setOnEditorActionListener(this);
        EditText text2 = (EditText)findViewById(R.id.text2);
        text2.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == IME_ACTION_SEARCH) {
            //Handle search key click
            return true;
        }
        if(actionId == IME_ACTION_GO) {
            //Handle go key click
            return true;
        }
        return false;
    }
}
