package com.examples.mediastore;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MediaActivity extends Activity implements View.OnClickListener {
 
    private static final int REQUEST_AUDIO = 1;
    private static final int REQUEST_VIDEO = 2;
    private static final int REQUEST_IMAGE = 3;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button images = (Button)findViewById(R.id.imageButton);
        images.setOnClickListener(this);
        Button videos = (Button)findViewById(R.id.videoButton);
        videos.setOnClickListener(this);
        Button audio = (Button)findViewById(R.id.audioButton);
        audio.setOnClickListener(this);
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       
        if(resultCode == Activity.RESULT_OK) {
            //Uri to user selection returned in the Intent
            Uri selectedContent = data.getData();
            if(requestCode == REQUEST_IMAGE) {
                //Display the image
            }
            if(requestCode == REQUEST_VIDEO) {
                //Play the video clip
            }
            if(requestCode == REQUEST_AUDIO) {
                //Play the audio clip
            }
        }
    }
    
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        switch(v.getId()) {
        case R.id.imageButton:
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_IMAGE);
            return;
        case R.id.videoButton:
            intent.setType("video/*");
            startActivityForResult(intent, REQUEST_VIDEO);
            return;
        case R.id.audioButton:
            intent.setType("audio/*");
            startActivityForResult(intent, REQUEST_AUDIO);
            return;
        default:
            return;
        }
    }
}