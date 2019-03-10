package com.sixteenmb.abhishekint.humor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishekint on 06-05-2017.
 */

public class MyAdapter extends FragmentPagerAdapter {
    List<String> list=new ArrayList<>();
    public MyAdapter(FragmentManager fm) {

        super(fm);
        list.add("WEB SERIES");
        list.add("COMEDY");
        list.add("CANDID");
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            Fragment fragment = new Web_Series();
            return fragment;
        }
        if (position == 1) {
            Fragment fragment = new Comedy();
            return fragment;
        }
        if(position==2)
        {
            Fragment fragment=new News();
            return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

            return list.get(position);

    }
}