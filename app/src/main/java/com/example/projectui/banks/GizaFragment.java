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
public class GizaFragment extends Fragment {


    public GizaFragment() {
        // Required empty public constructor
    }

    public int[] imageSource = {
            R.drawable.giza1,
            R.drawable.giza2,
            R.drawable.giza3,
            R.drawable.giza4,
            R.drawable.giza5,
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_giza, container, false);

        LinearLayout linearLayout1 = view.findViewById(R.id.ll_giza);
        for (int i = 0; i < 5; i++) {
            ImageView image = new ImageView(getActivity());
            image.setBackgroundResource(imageSource[i]);
            linearLayout1.addView(image);
        }

        return view;
    }

}
