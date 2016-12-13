package com.newbie.amien.projectbesar2.Home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newbie.amien.projectbesar2.R;

/**
 * Created by amien on 11/12/16.
 */

public class TabFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_itemms = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        inflate tab_layout
        View x = inflater.inflate(R.layout.tab_layout, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

//        iki gae nge-set Adapter View Pager
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

//        setupviewpager iku ndak jalan kalau ndak ada skrip ngisor iki, runnable
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;
    }

//    iki class di gae nge set tab-tab dan mau buka apa kalau tab dipilih
    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 : return new ListFragment();
                case 1 : return new MapFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_itemms;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "List";
                case 1: return "Map";
            }
            return null;
        }
    }
}
