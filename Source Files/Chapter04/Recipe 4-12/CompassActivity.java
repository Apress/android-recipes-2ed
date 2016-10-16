package com.examples.compass;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class CompassActivity extends Activity implements SensorEventListener {
    
    private SensorManager mSensorManager;
    private Sensor mAccelerometer, mField;
    private TextView valueView, directionView;
    
    private float[] mGravity;
    private float[] mMagnetic;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        
        valueView = (TextView)findViewById(R.id.values);
        directionView = (TextView)findViewById(R.id.direction);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, mField, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    private void updateDirection() {
        float[] temp = new float[9];
        float[] R = new float[9];
        //Load rotation matrix into R
        SensorManager.getRotationMatrix(temp, null, mGravity, mMagnetic);
        //Remap to camera's point-of-view
        SensorManager.remapCoordinateSystem(temp, SensorManager.AXIS_X, SensorManager.AXIS_Z, R);
        //Return the orientation values
        float[] values = new float[3];
        SensorManager.getOrientation(R, values);
        //Convert to degrees
        for (int i=0; i < values.length; i++) {
            Double degrees = (values[i] * 180) / Math.PI;
            values[i] = degrees.floatValue();
        }
        //Display the compass direction
        directionView.setText( getDirectionFromDegrees(values[0]) );
        //Display the raw values
        valueView.setText(String.format("Azimuth: %1$1.2f, Pitch: %2$1.2f, Roll: %3$1.2f",
                values[0], values[1], values[2]));
    }
    
    private String getDirectionFromDegrees(float degrees) {
        if(degrees >= -22.5 && degrees < 22.5) { return "N"; }
        if(degrees >= 22.5 && degrees < 67.5) { return "NE"; }
        if(degrees >= 67.5 && degrees < 112.5) { return "E"; }
        if(degrees >= 112.5 && degrees < 157.5) { return "SE"; }
        if(degrees >= 157.5 || degrees < -157.5) { return "S"; }
        if(degrees >= -157.5 && degrees < -112.5) { return "SW"; }
        if(degrees >= -112.5 && degrees < -67.5) { return "W"; }
        if(degrees >= -67.5 && degrees < -22.5) { return "NW"; }

        return null;
    }
    
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    public void onSensorChanged(SensorEvent event) {
        switch(event.sensor.getType()) {
        case Sensor.TYPE_ACCELEROMETER:
            mGravity = event.values.clone();
            break;
        case Sensor.TYPE_MAGNETIC_FIELD:
            mMagnetic = event.values.clone();
            break;
        default:
            return;
        }
        
        if(mGravity != null && mMagnetic != null) {
            updateDirection();
        }
    }
}