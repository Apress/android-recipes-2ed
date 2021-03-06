package ca.tutortutor.grayscale;

import android.app.Activity;

import android.os.Bundle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.renderscript.Allocation;
import android.renderscript.RenderScript;

import android.view.View;

import android.widget.ImageView;

public class GrayScale extends Activity
{
   boolean original = true;

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      final ImageView iv = new ImageView(this);
      iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
      iv.setImageResource(R.drawable.sol);
      setContentView(iv);
      iv.setOnClickListener(new View.OnClickListener() 
                            {
                               @Override
                               public void onClick(View v) 
                               {
                                  if (original)
                                     drawGS(iv, R.drawable.sol);
                                  else
                                     iv.setImageResource(R.drawable.sol);
                                  original = !original;
                               }
                            });
   }

   private void drawGS(ImageView iv, int imID)
   {
      Bitmap bmIn = BitmapFactory.decodeResource(getResources(), imID);
      Bitmap bmOut = Bitmap.createBitmap(bmIn.getWidth(), bmIn.getHeight(), 
                                         bmIn.getConfig());
      RenderScript rs = RenderScript.create(this);
      Allocation allocIn;
      allocIn = Allocation.createFromBitmap(rs, bmIn,
                                            Allocation.MipmapControl.MIPMAP_NONE,
                                            Allocation.USAGE_SCRIPT);
      Allocation allocOut = Allocation.createTyped(rs, allocIn.getType());
      ScriptC_grayscale script = new ScriptC_grayscale(rs, getResources(), 
                                                       R.raw.grayscale);
      script.set_in(allocIn);
      script.set_out(allocOut);
      script.set_script(script);
      script.invoke_filter();
      allocOut.copyTo(bmOut);
      iv.setImageBitmap(bmOut);
   }
}