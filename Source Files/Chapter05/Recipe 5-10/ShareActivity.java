package com.examples.share;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ShareActivity extends Activity implements AdapterView.OnItemClickListener {

    Cursor mCursor;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        String[] projection = new String[]{"_id", ImageProvider.COLUMN_NAME, ImageProvider.COLUMN_IMAGE};
        mCursor = managedQuery(ImageProvider.CONTENT_URI, projection, null, null, null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                mCursor, new String[]{ImageProvider.COLUMN_NAME}, new int[]{android.R.id.text1});
        
        ListView list = (ListView)findViewById(R.id.list);
        list.setOnItemClickListener(this);
        list.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        //Seek the cursor to the selection
        mCursor.moveToPosition(position);
        
        //Load the name column into the TextView
        TextView tv = (TextView)findViewById(R.id.name);
        tv.setText(mCursor.getString(1));
        
        ImageView iv = (ImageView)findViewById(R.id.image);
        try {
            //Load the content from the image column into the ImageView
            InputStream in = getContentResolver().openInputStream(Uri.parse(mCursor.getString(2)));
            Bitmap image = BitmapFactory.decodeStream(in);
            iv.setImageBitmap(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}