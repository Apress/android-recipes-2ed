package com.examples.popupmenus;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActionActivity extends Activity implements AbsListView.MultiChoiceModeListener {

    private static final String[] ITEMS =
        {"Mom", "Dad", "Brother", "Sister", "Uncle", "Aunt"};
    
    private ListView mList;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Register a button for context events
        mList = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, ITEMS);
        mList.setAdapter(adapter);
        mList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mList.setMultiChoiceModeListener(this);

        setContentView(mList);
    }
    
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        //You can do extra work here update the menu if the
        // ActionMode is ever invalidated
        return true;
    }
    
    @Override
    public void onDestroyActionMode(ActionMode mode) {
        //This is called when the action mode has ben exited
    }
    
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);
        return true;
    }
    
    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
      //Switch on the item’s ID to find the action the user selected
        switch(item.getItemId()) {
        case R.id.menu_delete:
            //Perform delete actions
            break;
        case R.id.menu_edit:
            //Perform edit actions
            break;
        default:
            return false;
        }
        return true;
    }
    
    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position,
            long id, boolean checked) {
        int count = mList.getCheckedItemCount();
        mode.setTitle(String.format("%d Selected", count));
    }
}
