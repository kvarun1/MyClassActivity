package com.mbinfo.adapter;

import android.content.Context;

import com.mbinfo.fragment.Fragment_One;
import com.mbinfo.fragment.Fragment_Two;
import com.mbinfo.fragment.HomeFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;
    public TabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                Fragment_One sportFragment = new Fragment_One();
                return sportFragment;
            case 2:
                Fragment_Two movieFragment = new Fragment_Two();
                return movieFragment;
            default:
                return null;
        }
    }
    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return totalTabs;
    }
}
