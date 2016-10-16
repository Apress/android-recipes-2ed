package com.examples.mapper;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MyActivity extends MapActivity {

    MapView map;
    MapController controller;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        map = (MapView)findViewById(R.id.map);
        controller = map.getController();
        
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        int lat, lng;
        if(location != null) {
            //Convert to microdegrees
            lat = (int)(location.getLatitude() * 1000000);
            lng = (int)(location.getLongitude() * 1000000);
        } else {
            //Default to Google HQ
            lat = 37427222;
            lng = -122099167;
        }
        GeoPoint mapCenter = new GeoPoint(lat,lng);
        controller.setCenter(mapCenter);
        controller.setZoom(15);
    }
    
    //Required abstract method, return false
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}