package com.examples.expandedlist;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class SimplerExpandableListAdapter extends BaseExpandableListAdapter {
	private Context mContext;
	private String[][] mContents;
	private String[] mTitles;
	
	public SimplerExpandableListAdapter(Context context, String[] titles, String[][] contents) {
		super();
		//Check arguments
		if(titles.length != contents.length) {
			throw new IllegalArgumentException("Titles and Contents must be the same size.");
		}
		
		mContext = context;
		mContents = contents;
		mTitles = titles;
	}
	
	//Return a child item
	@Override
	public String getChild(int groupPosition, int childPosition) {
		return mContents[groupPosition][childPosition];
	}

	//Return a item's id
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	//Return view for each item row
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView row = (TextView)convertView;
		if(row == null) {
			row = new TextView(mContext);
		}
		row.setText(mContents[groupPosition][childPosition]);
		return row;
	}

	//Return number of items in each section
	@Override
	public int getChildrenCount(int groupPosition) {
		return mContents[groupPosition].length;
	}

	//Return sections
	@Override
	public String[] getGroup(int groupPosition) {
		return mContents[groupPosition];
	}

	//Return the number of sections
	@Override
	public int getGroupCount() {
		return mContents.length;
	}

	//Return a section's id
	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	//Return a view for each section header
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView row = (TextView)convertView;
		if(row == null) {
			row = new TextView(mContext);
		}
		row.setTypeface(Typeface.DEFAULT_BOLD);
		row.setText(mTitles[groupPosition]);
		return row;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
