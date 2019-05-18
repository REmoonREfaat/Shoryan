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
public class CairoFragment extends Fragment {


    public CairoFragment() {
        // Required empty public constructor
    }

    public int[] imageSource = {
            R.drawable.cairo1,
            R.drawable.cairo2,
            R.drawable.cairo3,
            R.drawable.cairo4,
            R.drawable.cairo5,
            R.drawable.cairo6,
            R.drawable.cairo7,
            R.drawable.cairo8,
            R.drawable.cairo9,
            R.drawable.cairo10,
            R.drawable.cairo4,
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cairo, container, false);

        LinearLayout linearLayout1 = view.findViewById(R.id.ll_cairo);
        for (int i = 0; i < 10; i++) {
            ImageView image = new ImageView(getActivity());
            image.setBackgroundResource(imageSource[i]);
            linearLayout1.addView(image);
        }

        return view;
    }

}
