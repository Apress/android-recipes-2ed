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
        
        ArrayList<GeoPoint> locations = new ArrayList<GeoPoint>();
        ArrayList<Drawable> images = new ArrayList<Drawable>();
        //Google HQ 37.427,-122.099
        locations.add(new GeoPoint(37427222,-122099167));
        images.add(getResources().getDrawable(R.drawable.logo));
        //Subtract 0.01 degrees
        locations.add(new GeoPoint(37426222,-122089167));
        images.add(getResources().getDrawable(R.drawable.icon));
        //Add 0.01 degrees
        locations.add(new GeoPoint(37428222,-122109167));
        images.add(getResources().getDrawable(R.drawable.icon));
        
        LocationOverlay myOverlay = new LocationOverlay(this, getResources().getDrawable(R.drawable.icon));
        myOverlay.setItems(locations, images);
        map.getOverlays().add(myOverlay);
        controller.setCenter(locations.get(0));
        controller.setZoom(15);
    }
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}