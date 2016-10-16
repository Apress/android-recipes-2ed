package com.examples.files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

public class AssetActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        setContentView(tv);
        
        try {
            //Access application assets
            AssetManager manager = getAssets();
            //Open our data file
            InputStream mInput = manager.open("data.csv");
            //Read data in
            byte[] data = new byte[128];
            mInput.read(data);
            mInput.close();
            
            //Parse the CSV data and display
            String raw = new String(data);
            ArrayList<Person> cooked = parse(raw.trim());
            StringBuilder builder = new StringBuilder();
            for(Person piece : cooked) {
                builder.append(String.format("%s is %s years old, and likes the color %s",
                        piece.name, piece.age, piece.color));
                builder.append('\n');
            }
            tv.setText(builder.toString());
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
    /* Simple CSV Parser */
    private static final int COL_NAME = 0;
    private static final int COL_AGE = 1;
    private static final int COL_COLOR = 2;
    
    private ArrayList<Person> parse(String raw) {
        ArrayList<Person> results = new ArrayList<Person>();
        Person current = null;
        
        StringTokenizer st = new StringTokenizer(raw,",\n");
        int state = COL_NAME;
        while(st.hasMoreTokens()) {
            switch(state) {
            case COL_NAME:
                current = new Person();
                current.name = st.nextToken();
                state = COL_AGE;
                break;
            case COL_AGE:
                current.age = st.nextToken();
                state = COL_COLOR;
                break;
            case COL_COLOR:
                current.color = st.nextToken();
                results.add(current);
                state = COL_NAME;
                break;
            }
        }
        
        return results;
    }
    
    private class Person {
        public String name;
        public String age;
        public String color;
        
        public Person() { }
    }
}