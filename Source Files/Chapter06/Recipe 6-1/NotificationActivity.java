package com.examples.notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NotificationActivity extends Activity implements View.OnClickListener {
 
    private static final int NOTE_ID = 100;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        button.setText("Post New Notification");
        button.setOnClickListener(this);
        setContentView(button);
    }

    @Override
    public void onClick(View v) {
        //Run 10 seconds after click
        handler.postDelayed(task, 10000);
        Toast.makeText(this, "Notification will post in 10 seconds", Toast.LENGTH_SHORT).show();
    }
    
    private Handler handler = new Handler();
    private Runnable task = new Runnable() {
        @Override
        public void run() {
            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            Intent launchIntent = new Intent(getApplicationContext(), NotificationActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, launchIntent, 0);
            
            //Create notification with the time it was fired
            Notification note = new Notification(R.drawable.icon, "Something Happened", System.currentTimeMillis());
            //Set notification information
            note.setLatestEventInfo(getApplicationContext(), "We're Finished!", "Click Here!", contentIntent);
            note.defaults |= Notification.DEFAULT_SOUND;
            note.flags |= Notification.FLAG_AUTO_CANCEL;
            
            manager.notify(NOTE_ID, note);
        }
    };
}