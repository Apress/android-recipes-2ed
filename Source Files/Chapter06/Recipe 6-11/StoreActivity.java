package com.examples.mediastore;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StoreActivity extends Activity implements View.OnClickListener {

    private static final int REQUEST_CAPTURE = 100;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button images = (Button)findViewById(R.id.imageButton);
        images.setOnClickListener(this);
        Button videos = (Button)findViewById(R.id.videoButton);
        videos.setOnClickListener(this);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CAPTURE && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "All Done!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        ContentValues values;
        Intent intent;
        Uri storeLocation;
        
        switch(v.getId()) {
        case R.id.imageButton:
            //Create any metadata for image
            values = new ContentValues(2);
            values.put(MediaStore.Images.ImageColumns.DATE_TAKEN, System.currentTimeMillis());
            values.put(MediaStore.Images.ImageColumns.DESCRIPTION, "Sample Image");
            //Insert metadata and retrieve Uri location for file
            storeLocation = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            //Start capture with new location as destination
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, storeLocation);
            startActivityForResult(intent, REQUEST_CAPTURE);
            return;
        case R.id.videoButton:
            //Create any metadata for video
            values = new ContentValues(2);
            values.put(MediaStore.Video.VideoColumns.ARTIST, "Yours Truly");
            values.put(MediaStore.Video.VideoColumns.DESCRIPTION, "Sample Video Clip");
            //Insert metadata and retrieve Uri location for file
            storeLocation = getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
            //Start capture with new location as destination
            intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, storeLocation);
            startActivityForResult(intent, REQUEST_CAPTURE);
            return;
        default:
            return;
        }
    }
}
