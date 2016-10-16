package com.examples.optionsmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class OptionsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Use this callback to create the menu and do any
        // initial setup necessary
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //Use this callback to do setup that needs to happen
        // each time the menu opens
        return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Get the selected option by id
        switch (item.getItemId()) {
        case R.id.menu_add:
            //Do add action
            break;
        case R.id.menu_remove:
            //Do remove action
            break;
        case R.id.menu_edit:
            //Do edit action
            break;
        case R.id.menu_settings:
            //Do settings action
            break;
        default:
            break;
        }
        
        return true;
    }
}
