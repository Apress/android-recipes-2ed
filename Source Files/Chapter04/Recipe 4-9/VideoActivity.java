package com.examples.playback;

import java.io.File;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {

    VideoView videoView;
    MediaController controller;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Wire components to the view
        videoView = new VideoView(this);
        
        videoView.setVideoURI( Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"MysticHigh.mp4")) );
        controller = new MediaController(this);
        videoView.setMediaController(controller);
        videoView.start();
        
        setContentView(videoView);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();
    }
}
