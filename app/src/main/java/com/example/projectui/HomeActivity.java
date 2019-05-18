package com.example.projectui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.example.projectui.base.BaseActivity;
import com.example.projectui.inhomefragments.AddDonorFragment;
import com.example.projectui.inhomefragments.BanksFragment;
import com.example.projectui.inhomefragments.ContactUsFragment;
import com.example.projectui.inhomefragments.FragmentComment;
import com.example.projectui.inhomefragments.HelpFragment;
import com.example.projectui.inhomefragments.InfoFragment;
import com.example.projectui.inhomefragments.PersonalFragment;
import com.example.projectui.inhomefragments.SearchFragment;
import com.example.projectui.inhomefragments.ShareFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.projectui.base.BaseFragment.FRAGMENT_ADD_DONOR;
import static com.example.projectui.base.BaseFragment.FRAGMENT_BANKS;
import static com.example.projectui.base.BaseFragment.FRAGMENT_COMMENTS;
import static com.example.projectui.base.BaseFragment.FRAGMENT_CONTACT_US;
import static com.example.projectui.base.BaseFragment.FRAGMENT_HELP;
import static com.example.projectui.base.BaseFragment.FRAGMENT_INFO;
import static com.example.projectui.base.BaseFragment.FRAGMENT_WE;
import static com.example.projectui.base.BaseFragment.FRAGMENT_SEARCH;

public class HomeActivity extends BaseActivity
        implements HomeFragment.HomeFragmentInterface{


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    addFragment(new HomeFragment(), false);
                    return true;

                case R.id.navigation_help:
                    addFragment(new FragmentComment(), true);
                    return true;

                case R.id.navigation_share:

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);

               return true;

                case R.id.navigation_personal:
                    addFragment(new PersonalFragment(), true);
                    return true;

            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        addFragment(new HomeFragment(), false);


    }

    @Override
    public void onFragmentClicked(int fragmentKey) {
        switch (fragmentKey){
            case FRAGMENT_ADD_DONOR:
                addFragment(new AddDonorFragment(), true);
                break;
            case FRAGMENT_SEARCH:
                addFragment(new SearchFragment(), true);
                break;
            case FRAGMENT_BANKS:
                addFragment(new BanksFragment(), true);
                break;
            case FRAGMENT_INFO:
                addFragment(new InfoFragment(), true);
                break;
            case FRAGMENT_CONTACT_US:
                addFragment(new ContactUsFragment(), true);
                break;
            case FRAGMENT_WE:
                addFragment(new HelpFragment(), true);
                break;
            case FRAGMENT_COMMENTS:
                addFragment(new FragmentComment(), true);
                break;

        }
    }
}
