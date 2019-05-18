package com.example.projectui.entities;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.projectui.R;

import java.util.ArrayList;

public class MyListAdapter implements ListAdapter {
    ArrayList<DonorInfomation> arrayList;
    Context context;

    public MyListAdapter(Context context, ArrayList<DonorInfomation> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }

    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DonorInfomation subjectData=arrayList.get(position);
        if(convertView==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.mylist, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            TextView txtVDoctorName =convertView.findViewById(R.id.txtVName);
            TextView txtVDoctorMob =convertView.findViewById(R.id.txtVMob);
            TextView txtVGovernorate =convertView.findViewById(R.id.txtVGovernorate);
            TextView txtVbloodType =convertView.findViewById(R.id.txtVbloodType);
            TextView txtVDate =convertView.findViewById(R.id.txtVDate);
            txtVDoctorName.setText(subjectData.getName());
            txtVDoctorMob.setText(subjectData.getMobile());
            txtVGovernorate.setText(subjectData.getCountry());
            txtVbloodType.setText(subjectData.getBloodType());
            txtVDate.setText(subjectData.getTime());
        }

        return convertView;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}