package com.example.projectui.inhomefragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectui.HomeActivity;
import com.example.projectui.R;
import com.example.projectui.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends BaseFragment {


    public ContactUsFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_contact_us, container, false);


        // Create request to get image from filesystem when button clicked
        view.findViewById(R.id.btn_close)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i =  new Intent(getContext(), HomeActivity.class);
                        startActivity(i);
                    }
                });




        return view;
    }

}
