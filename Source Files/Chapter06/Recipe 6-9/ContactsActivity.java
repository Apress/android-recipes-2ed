package com.examples.contacts;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;

public class ContactsActivity extends ListActivity implements AdapterView.OnItemClickListener {

    Cursor mContacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Return all contacts, ordered by name
        String[] projection = new String[] { ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME };
        mContacts = managedQuery(ContactsContract.Contacts.CONTENT_URI,
                projection, null, null, ContactsContract.Contacts.DISPLAY_NAME);

        // Display all contacts in a ListView
        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, mContacts,
                new String[] { ContactsContract.Contacts.DISPLAY_NAME },
                new int[] { android.R.id.text1 });
        setListAdapter(mAdapter);
        // Listen for item selections
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        if (mContacts.moveToPosition(position)) {
            int selectedId = mContacts.getInt(0); // _ID column
            // Gather email data from email table
            Cursor email = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    new String[] { ContactsContract.CommonDataKinds.Email.DATA },
                    ContactsContract.Data.CONTACT_ID + " = " + selectedId, null, null);
            // Gather phone data from phone table
            Cursor phone = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER },
                    ContactsContract.Data.CONTACT_ID + " = " + selectedId, null, null);
            // Gather addresses from address table
            Cursor address = getContentResolver().query(
                    ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
                    new String[] { ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS },
                    ContactsContract.Data.CONTACT_ID + " = " + selectedId, null, null);

            //Build the dialog message
            StringBuilder sb = new StringBuilder();
            sb.append(email.getCount() + " Emails\n");
            if (email.moveToFirst()) {
                do {
                    sb.append("Email: " + email.getString(0));
                    sb.append('\n');
                } while (email.moveToNext());
                sb.append('\n');
            }
            sb.append(phone.getCount() + " Phone Numbers\n");
            if (phone.moveToFirst()) {
                do {
                    sb.append("Phone: " + phone.getString(0));
                    sb.append('\n');
                } while (phone.moveToNext());
                sb.append('\n');
            }
            sb.append(address.getCount() + " Addresses\n");
            if (address.moveToFirst()) {
                do {
                    sb.append("Address:\n" + address.getString(0));
                } while (address.moveToNext());
                sb.append('\n');
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(mContacts.getString(1)); // Display name
            builder.setMessage(sb.toString());
            builder.setPositiveButton("OK", null);
            builder.create().show();
            
            // Finish temporary cursors
            email.close();
            phone.close();
            address.close();
        }
    }
}