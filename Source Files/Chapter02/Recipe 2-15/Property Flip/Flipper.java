package com.examples.animateproperty;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class Flipper extends Activity {

    boolean isHeads;
    ObjectAnimator flipper;
    Bitmap headsImage, tailsImage;
    ImageView flipImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        headsImage = BitmapFactory.decodeResource(getResources(), R.drawable.heads);
        tailsImage = BitmapFactory.decodeResource(getResources(), R.drawable.tails);
        
        flipImage = (ImageView)findViewById(R.id.flip_image);
        flipImage.setImageBitmap(headsImage);
        isHeads = true;
        
        flipper = ObjectAnimator.ofFloat(flipImage, "rotationY", 0f, 360f);
        flipper.setDuration(500);
        flipper.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animation.getAnimatedFraction() >= 0.25f && isHeads) {
                    flipImage.setImageBitmap(tailsImage);
                    isHeads = false;
                }
                if (animation.getAnimatedFraction() >= 0.75f && !isHeads) {
                    flipImage.setImageBitmap(headsImage);
                    isHeads = true;
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            flipper.start();
            return true;
        }
        return super.onTouchEvent(event);
    }
}