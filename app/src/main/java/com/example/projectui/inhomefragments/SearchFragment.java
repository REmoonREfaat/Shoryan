package com.example.projectui.inhomefragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projectui.DonnersLists;
import com.example.projectui.DonorsMap;
import com.example.projectui.R;
import com.example.projectui.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    String country, paidType, method;
    Spinner spinnerCountry;
    RadioButton radioFree, radiopaid, radioBtn_lists, radioBtn_maps;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);

        radioFree = (RadioButton) view.findViewById(R.id.radioBtn_paymentFree);
        radiopaid = (RadioButton) view.findViewById(R.id.radioBtn_paymentPaid);

        radioBtn_lists = (RadioButton) view.findViewById(R.id.radioBtn_lists);
        radioBtn_maps = (RadioButton) view.findViewById(R.id.radioBtn_maps);

        spinnerCountry = (Spinner) view.findViewById(R.id.spinnerCountry);


        view.findViewById(R.id.btnSearch)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        radioData();
                        try {

                            if (radioBtn_maps.isChecked()) {
                                country = spinnerCountry.getSelectedItem().toString();
                                Intent intent = new Intent(getContext(), DonorsMap.class);
                                intent.putExtra("COUNTRY", country);
                                intent.putExtra("PAID_TYPE", paidType);
                                startActivity(intent);
                            }else if (radioBtn_lists.isChecked()){
                                country = spinnerCountry.getSelectedItem().toString();
                                Intent intent = new Intent(getContext(), DonnersLists.class);
                                intent.putExtra("COUNTRY", country);
                                intent.putExtra("PAID_TYPE", paidType);
                                startActivity(intent);
                            }else {
                                toastMessage("please check all radio button");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        return view;
    }

    public void radioData() {
        if (radiopaid.isChecked()) {
            paidType = "paid";
        } else if (radioFree.isChecked()) {
            paidType = "free";
        } else if (radioBtn_lists.isChecked()) {
            method = "list";
        } else if (radioBtn_maps.isChecked()) {
            method = "maps";
        } else {
            Toast.makeText(getContext(), "not check", Toast.LENGTH_LONG).show();
        }

    }


    public void showDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("تذكر اختيارك للبحث ")
                .setMessage(country + "\n" + paidType + "\n" + method)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }



}
