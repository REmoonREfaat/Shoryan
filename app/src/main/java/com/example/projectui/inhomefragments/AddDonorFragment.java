package com.example.projectui.inhomefragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectui.R;
import com.example.projectui.addImag;
import com.example.projectui.base.BaseFragment;
import com.example.projectui.entities.DonnerPojo;
import com.example.projectui.getLocation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddDonorFragment extends BaseFragment {

    private static final String TAG = "SelectImageActivity";
    private static final int REQUEST_CODE_IMAGE = 100;
    private static final int REQUEST_CODE_PERMISSIONS = 101;
    private static final String KEY_PERMISSIONS_REQUEST_COUNT = "KEY_PERMISSIONS_REQUEST_COUNT";
    private static final int MAX_NUMBER_REQUEST_PERMISSIONS = 3;

    private static final List<String> sPermissions = Arrays.asList(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION
    );

    private int mPermissionRequestCount;

    public AddDonorFragment() {
        // Required empty public constructor
    }


    EditText etMob, etName, et_email, et_age, et_time, et_mobile;
    RadioButton radioMale, radioFemale, radioFree, radiopaid;
    Spinner spinnerBlodType, spinnerCountry;
    String gender,bloodType, country;

    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //getting current user

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_donor, container, false);


        try {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            firebaseAuth = FirebaseAuth.getInstance();
        } catch (Exception e) {
          Log.e("tag",e.getMessage());
        }

        if (savedInstanceState != null) {
            mPermissionRequestCount =
                    savedInstanceState.getInt(KEY_PERMISSIONS_REQUEST_COUNT, 0);
        }

        etName =(EditText) view.findViewById(R.id.et_name);
        etMob = (EditText)view.findViewById(R.id.et_mobile);
        et_email = (EditText) view.findViewById(R.id.et_email);
        et_age = (EditText) view.findViewById(R.id.et_age);
        et_time = (EditText) view.findViewById(R.id.et_time);
        et_mobile = (EditText)  view.findViewById(R.id.et_mobile);

        radioFemale = (RadioButton) view.findViewById(R.id.radioBtn_female);
        radioMale = (RadioButton) view.findViewById(R.id.radioBtn_male);
        radioFree = (RadioButton) view.findViewById(R.id.radioBtn_paymentFreeAdd);
        radiopaid = (RadioButton) view.findViewById(R.id.radioBtn_paymentPaidAdd);

        spinnerBlodType = (Spinner) view.findViewById(R.id.spinnerBloodType);
        spinnerCountry = (Spinner) view.findViewById(R.id.spinnerCountry);

        // Make sure the app has correct permissions to run
        requestPermissionsIfNecessary();

        // Create request to get image from filesystem when button clicked
        view.findViewById(R.id.btn_upload_file)

                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(),addImag.class);
                        startActivity(i);
                    }
                });

        Intent i = new Intent(getContext(), getLocation.class);
        startActivity(i);

        // submitResult
        view.findViewById(R.id.btn_send)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {

                            radioData();
                            addDonner(etName.getText().toString(),
                                    etMob.getText().toString(),
                                    et_email.getText().toString(),
                                    et_age.getText().toString(),
                                    et_time.getText().toString(),
                                    gender,
                                    spinnerBlodType.getSelectedItem().toString(),
                                    spinnerCountry.getSelectedItem().toString(),
                                    getData()[1],
                                    getData()[0]);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

        return view;
    }

    public String generateCode() {
        // Creating a random UUID (Universally unique identifier).
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


    public void addDonner(String name, String mobile, String email, String age, String time, String gender, String bloodType, String country, String longitude, String latitude) {
        String paymentType = "f";
        if (radiopaid.isChecked()) {
            paymentType = "paid";
        } else if (radioFree.isChecked()) {
            paymentType = "free";
        }

            DonnerPojo donner = new DonnerPojo(name, mobile, email, age, time, gender, bloodType, country, paymentType, longitude, latitude);
            mDatabase.child("Donors").child(name).setValue(donner);
            toastMessage("تم الحفظ");

    }


    public void radioData() {
        if (radioMale.isChecked()) {
            gender = "male";
        } else if (radioFemale.isChecked()) {
            gender = "male";
        }
        else
            Toast.makeText(getContext(), "not check", Toast.LENGTH_LONG).show();
    }

    /**
     * Save the permission request count on a rotate
     **/

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PERMISSIONS_REQUEST_COUNT, mPermissionRequestCount);
    }

    /**
     * Request permissions twice - if the user denies twice then show a toast about how to update
     * the permission for storage. Also disable the button if we don't have access to pictures on
     * the device.
     */
    private void requestPermissionsIfNecessary() {
        if (!checkAllPermissions()) {
            if (mPermissionRequestCount < MAX_NUMBER_REQUEST_PERMISSIONS) {
                mPermissionRequestCount += 1;
                ActivityCompat.requestPermissions(
                        getActivity(),
                        sPermissions.toArray(new String[0]),
                        REQUEST_CODE_PERMISSIONS);
            } else {
                Toast.makeText(getContext(), R.string.set_permissions_in_settings,
                        Toast.LENGTH_LONG).show();
                view.findViewById(R.id.btn_upload_file).setEnabled(false);
            }
        }
    }

    private boolean checkAllPermissions() {
        boolean hasPermissions = true;
        for (String permission : sPermissions) {
            hasPermissions &=
                    ContextCompat.checkSelfPermission(
                            getContext(), permission) == PackageManager.PERMISSION_GRANTED;
        }
        return hasPermissions;
    }

    //get user name from sharedprefrence
    public String[] getData() {
        SharedPreferences sp = this.getActivity().getSharedPreferences("SHARE", Activity.MODE_PRIVATE);
        String Latitude = sp.getString("Latitude", "check location");
        String Longitude = sp.getString("Longitude", "check location");
        String governrate = sp.getString("governrate", "check location");
        String[] result = {Latitude, Longitude, governrate};
        return result;
    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

}
