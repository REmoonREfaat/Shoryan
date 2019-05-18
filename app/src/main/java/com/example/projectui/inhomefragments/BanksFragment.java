package com.example.projectui.inhomefragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectui.R;
import com.example.projectui.banks.ViewPagerAdapter;
import com.example.projectui.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class BanksFragment extends BaseFragment {

    public BanksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_banks, container, false);
        ViewPager viewPager= view.findViewById(R.id.vp_viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));

        TabLayout tabLayout= view.findViewById(R.id.tl_sliding);
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }

}