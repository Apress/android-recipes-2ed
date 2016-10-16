package com.examples.alarms;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

public class AlarmActivity extends Activity implements View.OnClickListener {
    
    private PendingIntent mAlarmIntent;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Attach the listener to both buttons
        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
        //Create the launch sender
        Intent launchIntent = new Intent(this, AlarmReceiver.class);
        mAlarmIntent = PendingIntent.getBroadcast(this, 0, launchIntent, 0);
    }

    @Override
    public void onClick(View v) {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        long interval = 5*1000; //5 seconds
        
        switch(v.getId()) {
        case R.id.start:
            Toast.makeText(this, "Scheduled", Toast.LENGTH_SHORT).show();
            manager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime()+interval,
                    interval,
                    mAlarmIntent);
            break;
        case R.id.stop:
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            manager.cancel(mAlarmIntent);
            break;
        default:
            break;
        }
    }
    
    //Return next 9AM start time
    private long nextStartTime() {
        long oneDay = 24*3600*1000; //24 hours
        //Get a Calendar (defaults to today)
        //Set the time to 09:00:00
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        
        //Get a Calendar at the current time
        Calendar now = Calendar.getInstance();
        
        if(now.before(startTime)) {
            //It's not 9AM yet
            return startTime.getTimeInMillis();
        } else {
            //Return 9AM tomorrow
            startTime.add(Calendar.DATE, 1);
            return startTime.getTimeInMillis();
        }
    }
}