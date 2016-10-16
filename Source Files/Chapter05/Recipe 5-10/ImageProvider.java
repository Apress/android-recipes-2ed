package com.examples.share;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public class ImageProvider extends ContentProvider {

    public static final Uri CONTENT_URI = Uri.parse("content://com.examples.share.imageprovider");
    
    public static final String COLUMN_NAME = "nameString";
    public static final String COLUMN_IMAGE = "imageUri";
    
    private String[] mNames;
    
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("This ContentProvider is read-only");
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("This ContentProvider is read-only");
    }

    @Override
    public boolean onCreate() {
        mNames = new String[] {"John Doe", "Jane Doe", "Jill Doe"};
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MatrixCursor cursor = new MatrixCursor(projection);
        for(int i = 0; i < mNames.length; i++) {
            //Insert only the columns they requested
            MatrixCursor.RowBuilder builder = cursor.newRow();
            for(String column : projection) {
                if(column.equals("_id")) {
                    //Use the array index as a unique id
                    builder.add(i);
                }
                if(column.equals(COLUMN_NAME)) {
                    builder.add(mNames[i]);
                }
                if(column.equals(COLUMN_IMAGE)) {
                    builder.add(Uri.withAppendedPath(CONTENT_URI, String.valueOf(i)));
                }
            }
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("This ContentProvider is read-only");
    }
    
    @Override
    public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException {
        int requested = Integer.parseInt(uri.getLastPathSegment());
        AssetFileDescriptor afd;
        AssetManager manager = getContext().getAssets();
        //Return the appropriate asset for the requested item
        try {
            switch(requested) {
            case 0:
                afd = manager.openFd("logo1.png");
                break;
            case 1:
                afd = manager.openFd("logo2.png");
                break;
            case 2:
                afd = manager.openFd("logo3.png");
                break;
            default:
                afd = manager.openFd("logo1.png");
            }            
            return afd;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } 
    }
}
