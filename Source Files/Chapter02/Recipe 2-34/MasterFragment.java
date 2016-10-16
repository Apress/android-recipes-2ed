package com.examples.fragmentsample;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.examples.fragmentsample.DataFragment.DataItem;

public class MasterFragment extends DialogFragment implements AdapterView.OnItemClickListener {

    /*
     * Callback interface to feed data selections up to the parent Activity
     */
    public interface OnItemSelectedListener {
        public void onDataItemSelected(DataItem selected);
    }
    
    /*
     * Factory method to create new Fragments
     */
    public static MasterFragment newInstance() {
        return new MasterFragment();
    }
    
    private ArrayAdapter<DataItem> mAdapter;
    private OnItemSelectedListener mItemSelectedListener;
    
    /*
     * Using onAttach to connect the listener interface, and guarantee that the
     * Activity we attach to supports the interface.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mItemSelectedListener = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Activity must implement OnItemSelectedListener");
        }
    }
    
    /*
     * Construct a custom adapter to display the name field from our data model.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mAdapter = new ArrayAdapter<DataFragment.DataItem>(getActivity(), android.R.layout.simple_list_item_1) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View row = convertView;
                if (row == null) {
                    row = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                }
                
                DataItem item = getItem(position);
                TextView tv = (TextView) row.findViewById(android.R.id.text1);
                tv.setText(item.getName());
                
                return row;
            }
        };
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ListView list = new ListView(getActivity());
        list.setOnItemClickListener(this);
        list.setAdapter(mAdapter);
        return list;
    }
    
    /*
     * onCreateDialog is the opportunity to directly access the Dialog that will be shown.
     * We use this callback to set the title of the dialog
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("Select a Site");
        
        return dialog;
    }
    
    /*
     * When the Fragment resumes, get the latest model information from our DataFragment
     */
    @Override
    public void onResume() {
        super.onResume();
        //Get the latest data list
        DataFragment fragment = (DataFragment) getFragmentManager().findFragmentByTag(DataFragment.TAG);
        if (fragment != null) {
            mAdapter.clear();
            for (DataItem item : fragment.getLatestData()) {
                mAdapter.add(item);
            }
            mAdapter.notifyDataSetChanged();
        }
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // Notify the Activity
        mItemSelectedListener.onDataItemSelected(mAdapter.getItem(position));
        
        // Hide the dialog, if shown.  This returns false when the Fragment
        // is embedded in the view.
        if (getShowsDialog()) {
            dismiss();
        }
    }
}
