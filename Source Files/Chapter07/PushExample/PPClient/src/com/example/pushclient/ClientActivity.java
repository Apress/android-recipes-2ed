package com.example.pushclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ClientActivity extends Activity {
    
    private Intent serviceIntent;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        serviceIntent = new Intent(this, ClientService.class);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.menu_start:
            startService(serviceIntent);
            return true;
        case R.id.menu_stop:
            stopService(serviceIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }  
}