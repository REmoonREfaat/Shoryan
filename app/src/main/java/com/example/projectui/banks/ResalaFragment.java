package com.example.projectui.banks;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.projectui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResalaFragment extends Fragment {


    public ResalaFragment() {
        // Required empty public constructor
    }

    public int[] imageSource = {
            R.drawable.resala1,
            R.drawable.resala2,
            R.drawable.resala3,
            R.drawable.resala4,
            R.drawable.resala5,
            R.drawable.resala6,
            R.drawable.resala7,
            R.drawable.resala8,
            R.drawable.resala9,
            R.drawable.resala10,
            R.drawable.resala11,
            R.drawable.resala12,
            R.drawable.resala13,
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resala, container, false);

        LinearLayout linearLayout1 = view.findViewById(R.id.ll_resala);
        for (int i = 0; i < 13; i++) {
            ImageView image = new ImageView(getActivity());
            image.setBackgroundResource(imageSource[i]);
            linearLayout1.addView(image);
        }

        return view;
    }

}
