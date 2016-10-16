package com.examples.matchmaker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class RootActivity extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        button.setOnClickListener(this);
        setContentView(button);
    }
    
    @Override
    public void onClick(View v) {
//        Intent intent = new Intent();
//        intent.setAction(PlayerActivity.ACTION_PLAY);
//        startActivity(intent);
        
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file:///android_asset/movie.m4v"), "video/h264");
        startActivity(Intent.createChooser(intent, "Play Video"));
    }
}
