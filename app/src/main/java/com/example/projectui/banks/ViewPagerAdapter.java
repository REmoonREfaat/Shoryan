package com.example.projectui.banks;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.projectui.R;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] childFragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        childFragments = new Fragment[]{
                new CairoFragment(),
                new GizaFragment(),
                new AlexFragment()
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CairoFragment(); //ChildFragment1 at position 0
            case 1:
                return new GizaFragment(); //ChildFragment2 at position 1
            case 2:
                return new AlexFragment(); //ChildFragment3 at position 2
            case 3:
                return new ResalaFragment(); //ChildFragment4 at position 3
        }
//        return childFragments[position];
        return null;
    }

    @Override
    public int getCount() {
        return 4; //three fragments
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "القاهرة";
            case 1:
                return "الجيزة";
            case 2:
                return "الأسكندرية";
            case 3:
                return "جمعية رسالة";
        }
        return null; //does not happen*/

    }

}