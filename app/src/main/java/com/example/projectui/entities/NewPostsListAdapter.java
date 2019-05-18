package com.example.projectui.entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectui.R;

import java.util.List;

public class NewPostsListAdapter extends ArrayAdapter<CommentsPojo> {
    Context context;
    List<CommentsPojo> itemsList = null;
    PostLikeClickListener mPostLikeClickListener = null;

    public NewPostsListAdapter(Context context,
                               List<CommentsPojo> objects,
                               PostLikeClickListener postLikeClickListener)
    {
        super(context, -1, objects);
        this.context = context;
        this.itemsList = objects;
        this.mPostLikeClickListener = postLikeClickListener;

    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater vi = LayoutInflater.from(context);
            rowView = vi.inflate(R.layout.post_row, null);
        }

        CommentsPojo currentItem = itemsList.get(position);
        bindItemView(currentItem, rowView);


        return rowView;
    }

    private void bindItemView(final CommentsPojo currentItem, View rowView) {
        if (currentItem == null || rowView == null) return;

        TextView txt_user_name = rowView.findViewById(R.id.txt_user_name);
        TextView txt_content = rowView.findViewById(R.id.txt_content);
        TextView txt_like_numbers = rowView.findViewById(R.id.like_nombers);
        ImageView iv_love = rowView.findViewById(R.id.love);

        txt_user_name.setText(currentItem.getUserId());
        txt_content.setText(currentItem.getCommentContent());
        txt_like_numbers.setText(currentItem.getLikeCounter()+"");

        if (currentItem.isUserLike) {
            iv_love.setImageResource(R.drawable.like_2);
        } else
            iv_love.setImageResource(R.drawable.like_1);

        if (mPostLikeClickListener != null) {
            iv_love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPostLikeClickListener.onPostLikeChanged(currentItem,getPosition(currentItem));
                }
            });
        }
    }


    @Override
    public int getViewTypeCount() {
        if (itemsList != null)
            return itemsList.size();
        else
            return 0;
    }


    public void updateItemsList(List<CommentsPojo> objects) {
        this.itemsList = objects;
        notifyDataSetChanged();
    }


    public interface PostLikeClickListener {
        void onPostLikeChanged(CommentsPojo currentItem,int position);
    }
}
