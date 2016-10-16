package com.exaples.webimage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class WebImageActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        WebImageView imageView = (WebImageView)findViewById(R.id.webImage);
        imageView.setPlaceholderImage(R.drawable.icon);
        imageView.setImageUrl("http://apress.com/resource/weblogo/Apress_120x90.gif");
    }
}
