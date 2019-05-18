package com.example.projectui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.projectui.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    HomeFragmentInterface mHomeFragmentInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeFragmentInterface)
            mHomeFragmentInterface = (HomeFragmentInterface) context;
        else throw (new RuntimeException("HomeActivity isn't instanceof HomeFragmentInterface"));
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    View view;
    ImageView addDonorImageView;
    ImageView searchImageView;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        addDonorImageView = view.findViewById(R.id.iv_add_donor);
        addDonorImageView.setOnClickListener(this);

        searchImageView = view.findViewById(R.id.iv_search);
        searchImageView.setOnClickListener(this);

        searchImageView = view.findViewById(R.id.iv_banks);
        searchImageView.setOnClickListener(this);

        searchImageView = view.findViewById(R.id.iv_info);
        searchImageView.setOnClickListener(this);

        searchImageView = view.findViewById(R.id.iv_we);
        searchImageView.setOnClickListener(this);

        searchImageView = view.findViewById(R.id.iv_contact_us);
        searchImageView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add_donor:
                mHomeFragmentInterface.onFragmentClicked(BaseFragment.FRAGMENT_ADD_DONOR);
                break;
            case R.id.iv_search:
                mHomeFragmentInterface.onFragmentClicked(BaseFragment.FRAGMENT_SEARCH);
                break;
            case R.id.iv_banks:
                mHomeFragmentInterface.onFragmentClicked(BaseFragment.FRAGMENT_BANKS);
                break;
            case R.id.iv_info:
                mHomeFragmentInterface.onFragmentClicked(BaseFragment.FRAGMENT_INFO);
                break;
            case R.id.iv_we:
                mHomeFragmentInterface.onFragmentClicked(BaseFragment.FRAGMENT_WE);
                break;
            case R.id.iv_contact_us:
                mHomeFragmentInterface.onFragmentClicked(BaseFragment.FRAGMENT_CONTACT_US);
                break;
        }
    }

    public interface HomeFragmentInterface {
        void onFragmentClicked(int fragmentKey);
    }

}
