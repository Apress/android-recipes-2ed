package com.examples.animateviewproperty;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnimateActivity extends Activity implements View.OnClickListener {

    View viewToAnimate;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button = (Button)findViewById(R.id.toggleButton);
        button.setOnClickListener(this);
        
        viewToAnimate = findViewById(R.id.theView);
    }
    
    @Override
    public void onClick(View v) {
        if(viewToAnimate.getAlpha() > 0f) {
            //If the view is visible already, slide it out to the right
            viewToAnimate.animate().alpha(0f).translationX(500f);
        } else {
            //If the view is hidden, do a fade-in in-place
            //Property Animations actually modify the view, so
            // we have to reset the view's location first
            viewToAnimate.setTranslationX(0f);
            viewToAnimate.animate().alpha(1f);
        }
    }
}
