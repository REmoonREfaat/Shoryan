package com.example.projectui.inhomefragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectui.R;
import com.example.projectui.base.BaseFragment;
import com.example.projectui.entities.CommentsPojo;
import com.example.projectui.entities.CommentsPojoAdd;
import com.example.projectui.entities.NewPostsListAdapter;
import com.example.projectui.entities.PostAdapters;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentComment extends BaseFragment implements NewPostsListAdapter.PostLikeClickListener {


    public FragmentComment() {
        // Required empty public constructor
    }

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    private FirebaseAuth firebaseAuth;
    String curentUser;
    String userEmail;
    ListView listVComments;
    int likeCounter = 0;
    boolean isUserLike = false;

    NewPostsListAdapter mNewPostsListAdapter;
    public View view;
    ArrayList<CommentsPojo> arrayList = new ArrayList<CommentsPojo>();
    ArrayList<CommentsPojo> arrayNew = new ArrayList<CommentsPojo>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_comment, container, false);
        //// TODO: 04/05/2019 make comments

        listVComments = (ListView) view.findViewById(R.id.listVComments);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference();

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            curentUser = user.getUid();
            userEmail = user.getEmail();
        }
        getPosts();

        view.findViewById(R.id.fab)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddCommentDialog();
                    }
                });


        return view;
    }

    public void reresh() {
        // Reload current fragment
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();
    }


    public void getPosts() {
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference databaseReference;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference();
        Query query = databaseReference.child("Posts").orderByChild("userId");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        CommentsPojo note = issue.getValue(CommentsPojo.class);
                        note.setUserId(userEmail);
                        arrayList.add(note);
                    }

                    if (mNewPostsListAdapter == null)
                        mNewPostsListAdapter = new NewPostsListAdapter(getActivity(), arrayList, FragmentComment.this);
                    else
                        mNewPostsListAdapter.updateItemsList(arrayList);


                    listVComments.setAdapter(mNewPostsListAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ram", databaseError.getDetails());
            }
        });
    }

    public void AddCommentDialog() {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                try {
                                    addPost(generateCode(), userInput.getText().toString(), likeCounter, isUserLike);
                                    arrayList.clear();
                                    getPosts();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    public void addPost(String nodeId, String commentContent, int likeCounter, boolean isUserLike) {
        try {
            CommentsPojoAdd commentsPojoAdd = new CommentsPojoAdd(nodeId, commentContent, curentUser, likeCounter, isUserLike);
            databaseReference.child("Posts").child(nodeId).setValue(commentsPojoAdd);
            toastMessage("تم الحفظ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData(String nodeId) {
        getIsUserLike(nodeId);
        getLikeCounter(nodeId);

        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference databaseReference;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference().child("Posts");
        databaseReference.child(nodeId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!isUserLike) {
                    dataSnapshot.getRef().child("likeCounter").setValue(likeCounter + 1);
                    dataSnapshot.getRef().child("userLike").setValue(true);


                } else {
                    dataSnapshot.getRef().child("likeCounter").setValue(likeCounter - 1);
                    dataSnapshot.getRef().child("userLike").setValue(false);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("User", databaseError.getMessage());
            }
        });

    }

    public void test() {

        DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("Posts");
        Query query = zonesRef;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        CommentsPojo note = issue.getValue(CommentsPojo.class);
                        note.setUserId(userEmail);
                        arrayNew.add(note);
                    }
//                   Log.e("tttt",arrayList.get(0).getName()+""+arrayList.get(1).getName());
                    PostAdapters customAdapter = new PostAdapters(getContext(), arrayNew);
                    listVComments.setAdapter(customAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ram", databaseError.getDetails());
            }
        });
    }


    public void getLikeCounter(final String id) {
        if(TextUtils.isEmpty(id))return;
        DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("Posts").child(id);
        Query query = zonesRef;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    CommentsPojo note = dataSnapshot.getValue(CommentsPojo.class);
                    likeCounter = note.getLikeCounter();
                    Log.e("JJ", likeCounter + "");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ram", databaseError.getDetails());
            }
        });

    }

    public void getIsUserLike(String id) {
        if(TextUtils.isEmpty(id))return;
        final boolean[] result = new boolean[1];
        DatabaseReference zonesRef = FirebaseDatabase.getInstance().getReference("Posts").child(id);
        Query query = zonesRef;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    CommentsPojo note = dataSnapshot.getValue(CommentsPojo.class);
                    isUserLike = note.isUserLike();
                    Log.e("ZZ", isUserLike + "");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ram", databaseError.getDetails());
            }
        });

    }

    public String generateCode() {
        // Creating a random UUID (Universally unique identifier).
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onPostLikeChanged(CommentsPojo currentItem,int position) {
        arrayList.get(position).setUserLike(!currentItem.isUserLike());
        if(arrayList.get(position).isUserLike())
            arrayList.get(position).setLikeCounter(currentItem.getLikeCounter()+1);
        else
            arrayList.get(position).setLikeCounter(currentItem.getLikeCounter()-1);

        mNewPostsListAdapter.updateItemsList(arrayList);
        updateData(currentItem.getNodeId());
    }


    public void updateComment(CommentsPojo currentItem){
        //update this record in the database

    }
}
