package com.examples.imagemask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.widget.ImageView;

public class MaskActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView iv = new ImageView(this);
        
        //Create and load images (immutable, typically)
        Bitmap source = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        Bitmap mask = BitmapFactory.decodeResource(getResources(), R.drawable.triangle);
        
        //Create a *mutable* location, and a canvas to draw into it
        Bitmap result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        
        RectF rect = new RectF(0,0,source.getWidth(),source.getHeight());
        float radius = 25.0f;
        paint.setColor(Color.BLACK);
        canvas.drawRoundRect(rect, radius, radius, paint);
        //canvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        paint.setXfermode(null);
        
        iv.setImageBitmap(result);
        setContentView(iv);
    }
}