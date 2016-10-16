package com.examples.backup;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

public class BackupActivity extends Activity implements BackupTask.CompletionListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Dummy example database
        SQLiteDatabase db = openOrCreateDatabase("mydb", Activity.MODE_PRIVATE, null);
        db.close();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        if( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
            BackupTask task = new BackupTask(this);
            task.setCompletionListener(this);
            task.execute(BackupTask.COMMAND_RESTORE);
        }
    }
    
    @Override
    public void onPause() {
        super.onPause();
        if( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
            BackupTask task = new BackupTask(this);
            task.execute(BackupTask.COMMAND_BACKUP);
        }
    }

    @Override
    public void onBackupComplete() {
        Toast.makeText(this, "Backup Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(int errorCode) {
        if(errorCode == BackupTask.RESTORE_NOFILEERROR) {
            Toast.makeText(this, "No Backup Found to Restore", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error During Operation: "+errorCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRestoreComplete() {
        Toast.makeText(this, "Restore Successful", Toast.LENGTH_SHORT).show();
    }
}