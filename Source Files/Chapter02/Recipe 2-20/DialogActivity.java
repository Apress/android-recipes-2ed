package com.examples.dialogs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DialogActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Activity");
        TextView tv = new TextView(this);
        tv.setText("I'm Really An Activity!");
        //Add some padding to keep the dialog borders away
        tv.setPadding(15, 15, 15, 15);
        setContentView(tv);
    }
}
