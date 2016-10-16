package com.examples.animationxml;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Flipper extends Activity {

    boolean isHeads;
    Animation shrink, grow;
    ImageView flipImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        flipImage = (ImageView)findViewById(R.id.flip_image);
        flipImage.setImageResource(R.drawable.heads);
        isHeads = true;

        shrink = AnimationUtils.loadAnimation(this, R.anim.shrink);
        shrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                if(isHeads) {
                    isHeads = false;
                    flipImage.setImageResource(R.drawable.tails);
                } else {
                    isHeads = true;
                    flipImage.setImageResource(R.drawable.heads);
                }
                flipImage.startAnimation(grow);
            }
        });
        grow = AnimationUtils.loadAnimation(this, R.anim.grow);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            flipImage.startAnimation(shrink);
            return true;
        }
        return super.onTouchEvent(event);
    }
}