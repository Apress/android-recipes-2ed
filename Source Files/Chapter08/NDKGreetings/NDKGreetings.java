package ca.tutortutor.ndkgreetings;
 
import android.app.Activity;
import android.app.AlertDialog;

import android.os.Bundle;
 
public class NDKGreetings extends Activity 
{
   static 
   {
      System.loadLibrary("NDKGreetings");
   }

   private native String getGreetingMessage();

   @Override
   public void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
      String greeting = getGreetingMessage();
      new AlertDialog.Builder(this).setMessage(greeting).show();
   }
}