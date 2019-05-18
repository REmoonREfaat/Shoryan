package com.example.projectui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.projectui.R;

public class BaseActivity extends AppCompatActivity {


    public void addFragment(BaseFragment fragment, boolean isAddToBackStack) {
        addFragment(fragment, isAddToBackStack, false);
    }

    public void addFragment(BaseFragment fragment, boolean isAddToBackStack, boolean isAllowRepeatFragment) {
        if (fragment == null) return;

        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.main_fragment);
        if (!isAllowRepeatFragment && currentFragment != null &&
                currentFragment.getClass().getName() == fragment.getClass().getName()
        ) {
            return;
        }

        FragmentTransaction ft = fm.beginTransaction()
                .replace(R.id.main_fragment, fragment);
        if (isAddToBackStack)
            ft.addToBackStack(fragment.getClass().getName());
        ft.commit();
    }

}
