package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Belal on 2/3/2016.
 */
//Extending FragmentStatePagerAdapter
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    
    ArrayList<String> tabCount;
 
    public ViewPagerAdapter(FragmentManager fm, ArrayList<String> tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }
 
    @Override
    public Fragment getItem(int position) {
                ViewPagerItemFragment tab1 = ViewPagerItemFragment.newInstance("",tabCount.get(position));
                return tab1;
    }
 
    @Override
    public int getCount() {
        return tabCount.size();
    }
}