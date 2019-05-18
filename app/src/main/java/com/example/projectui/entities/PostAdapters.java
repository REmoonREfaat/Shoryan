package com.example.projectui.entities;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectui.R;
import com.example.projectui.inhomefragments.FragmentComment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostAdapters implements ListAdapter {
    ArrayList<CommentsPojo> arrayList;
    Context context;
    FragmentComment fragmentComment = new FragmentComment();

    public PostAdapters(Context context, ArrayList<CommentsPojo> arrayList) {
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        CommentsPojo subjectData=arrayList.get(position);
        if(convertView==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.post_row, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

            TextView txt_user_name =convertView.findViewById(R.id.txt_user_name);
            TextView txt_content =convertView.findViewById(R.id.txt_content);
            ImageView love = convertView.findViewById(R.id.love);

            TextView like_nombers =convertView.findViewById(R.id.like_nombers);

            love.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                   fragmentComment.getLikeCounter(arrayList.get(position).getNodeId());
//                   fragmentComment.getIsUserLike(arrayList.get(position).getNodeId());
                    try {

                        fragmentComment.updateData(arrayList.get(position).getNodeId());


                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
            });

            txt_user_name.setText(subjectData.getUserId());
            txt_content.setText(subjectData.getCommentContent());
            like_nombers.setText(String.valueOf(subjectData.getLikeCounter()));

            if (subjectData.isUserLike){
                love.setImageResource(R.drawable.like_2);
            }else
                love.setImageResource(R.drawable.like_1);


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