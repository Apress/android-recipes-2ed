package com.examples.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class PagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewPager pager = new ViewPager(this);
        pager.setAdapter(new ImagePagerAdapter(this));
        
        setContentView(pager);
    }
}
