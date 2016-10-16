package com.examples.sharedb;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView;
import android.widget.Toast;

public class ShareActivity extends ListActivity implements AdapterView.OnItemClickListener {
    
    Cursor mCursor;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] projection = new String[]{FriendProvider.Columns._ID,
                FriendProvider.Columns.FIRST};
        mCursor = managedQuery(FriendProvider.CONTENT_URI, projection, null, null, null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, mCursor,
                new String[]{FriendProvider.Columns.FIRST},
                new int[]{android.R.id.text1});
        
        ListView list = getListView();
        list.setOnItemClickListener(this);
        list.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        mCursor.moveToPosition(position);
        
        Uri uri = Uri.withAppendedPath(FriendProvider.CONTENT_URI, mCursor.getString(0));
        String[] projection = new String[]{FriendProvider.Columns.FIRST,
                FriendProvider.Columns.LAST,
                FriendProvider.Columns.PHONE};
        //Get the full record
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        cursor.moveToFirst();
        
        String message = String.format("%s %s, %s", cursor.getString(0), cursor.getString(1), cursor.getString(2));
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}