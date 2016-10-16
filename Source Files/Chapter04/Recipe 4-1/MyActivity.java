package com.examples.mylocation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

public class MyActivity extends Activity {

    LocationManager manager;
    Location currentLocation;
    String selectedProvider;
    
    TextView locationView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationView = new TextView(this);
        setContentView(locationView);
        
        manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //Ask the user to enable GPS
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Location Manager");
            builder.setMessage("We would like to use your location, but GPS is currently disabled.\n"
                    +"Would you like to change these settings now?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Launch settings, allowing user to make a change
                    Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(i);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //No location service, no Activity
                    finish();
                }
            });
            builder.create().show();
        }
        //Get a cached location, if it exists
        currentLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        updateDisplay();
        //Register for updates
        int minTime = 5000;
        float minDistance = 0;
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, listener);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        manager.removeUpdates(listener);
    }
    
    private void updateDisplay() {
        if(currentLocation == null) {
            locationView.setText("Determining Your Location...");
        } else {
            locationView.setText(String.format("Your Location:\n%.2f, %.2f",
                    currentLocation.getLatitude(),
                    currentLocation.getLongitude()));
        }
    }
    
    private LocationListener listener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            currentLocation = location;
            updateDisplay();
        }

        @Override
        public void onProviderDisabled(String provider) { }

        @Override
        public void onProviderEnabled(String provider) { }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { }
        
    };
}