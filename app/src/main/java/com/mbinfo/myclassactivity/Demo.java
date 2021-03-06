package com.mbinfo.myclassactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.mbinfo.adapter.TabAdapter;
import com.mbinfo.fragment.Fragment_One;
import com.mbinfo.util.SendMessage;

public class Demo extends AppCompatActivity implements SendMessage {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
       tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Image"));
        tabLayout.addTab(tabLayout.newTab().setText("Video"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
         final TabAdapter adapter = new TabAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void sendData(String messge) {
        String tag = "android:switcher:" + R.id.viewpager + ":" + 1;
        Fragment_One f = (Fragment_One) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(messge);
    }
}