package com.example.projectui;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectui.entities.DonnerPojo;
import com.example.projectui.entities.DonorInfomation;
import com.example.projectui.entities.MyListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DonnersLists extends AppCompatActivity {

    private static final String TAG = "ViewDatabase";
    String country,paidType;

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private ListView mListView;

    ArrayList<DonorInfomation> arrayList = new ArrayList<DonorInfomation>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donners_lists);

        mListView = (ListView) findViewById(R.id.listview);

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        try {
            country = getIntent().getStringExtra("COUNTRY");
            paidType = getIntent().getStringExtra("PAID_TYPE");
            Log.e("RR",country+"\n"+paidType);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Query query = myRef.child("Donors");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {

                        for (DataSnapshot issue : dataSnapshot.getChildren()) {
                            DonorInfomation note = issue.getValue(DonorInfomation.class);
                            if (note.getCountry().equals(country)&&note.getPaymentType().equals(paidType)){
                                arrayList.add(note);
                            }
                        }
                        if (arrayList.isEmpty()) {
                            toastMessage("no result");
                        }
                        else {
                            MyListAdapter customAdapter = new MyListAdapter(getApplicationContext(), arrayList);
                            mListView.setAdapter(customAdapter);
                        }
    //                   Log.e("tttt",arrayList.get(0).getName()+""+arrayList.get(1).getName());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ram",databaseError.getDetails());
            }
        });



    }


    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
