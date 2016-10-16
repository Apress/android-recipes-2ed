package com.examples.mapper;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class MyActivity extends MapActivity {

    MapView map;
    MapController controller;
    MyLocationOverlay myOverlay;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        map = (MapView)findViewById(R.id.map);
        
        myOverlay = new MyLocationOverlay(this, map);
        map.getOverlays().add(myOverlay);        
    }

    @Override
    public void onResume() {
        super.onResume();
        myOverlay.enableMyLocation();
    }
    
    @Override
    public void onPause() {
        super.onResume();
        myOverlay.disableMyLocation();
    }
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}