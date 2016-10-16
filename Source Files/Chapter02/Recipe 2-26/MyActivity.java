package com.examples.expandedlist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class MyActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set up an expandable list
        ExpandableListView list = new ExpandableListView(this);
        list.setGroupIndicator(null);
        list.setChildIndicator(null);
        //Set up simple data and the new adapter
        String[] titles = {"Fruits","Vegetables","Meats"};
        String[] fruits = {"Apples","Oranges"};
        String[] veggies = {"Carrots","Peas","Broccoli"};
        String[] meats = {"Pork","Chicken"};
        String[][] contents = {fruits,veggies,meats};
        SimplerExpandableListAdapter adapter = new SimplerExpandableListAdapter(this,
            titles, contents);

        list.setAdapter(adapter);
        setContentView(list);

    }
}