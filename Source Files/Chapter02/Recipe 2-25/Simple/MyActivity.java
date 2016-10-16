package com.examples.listexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyActivity extends Activity {
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.custom_row,
                R.id.line1,
                new String[] {"Bill","Tom","Sally","Jenny"});
        list.setAdapter(adapter);

        setContentView(list);
    }
    
}