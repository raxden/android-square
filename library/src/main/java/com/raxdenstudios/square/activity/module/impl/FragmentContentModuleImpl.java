package com.raxdenstudios.square.activity.module.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.FragmentContentModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;
import com.raxdenstudios.square.fragment.OnBackPressedListener;

/**
 * Created by agomez on 02/06/2015.
 */
public class FragmentContentModuleImpl extends ModuleActivityImpl implements FragmentContentModule.FragmentContentModuleListener {

    private static String TAG = FragmentContentModuleImpl.class.getSimpleName();

    private View mContentFragmentView;
    private FragmentContentModule mCallbacks;

    public FragmentContentModuleImpl(ModularActivity activity) {
        if (!(activity instanceof FragmentContentModule)) {
            throw new IllegalStateException("Activity must implement FragmentContentModule.");
        }
        mCallbacks = (FragmentContentModule)activity;
    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);
        mContentFragmentView = mCallbacks.onCreateContentFragmentView(savedInstanceState);
        if (mContentFragmentView != null && savedInstanceState == null) {
            replaceFragment(mCallbacks.initContentFragment());
        }
        mCallbacks.onModuleLoaded(this);
    }

    @Override
    public boolean onModuleBackPressed(Context context) {
        if (getFragment() != null && getFragment() instanceof OnBackPressedListener) {
            if (((OnBackPressedListener)getFragment()).onBackPressed()) {
                return true;
            }
        }
        return super.onModuleBackPressed(context);
    }

    @Override
    public void onModuleDestroy(Context context) {
        super.onModuleDestroy(context);
        mCallbacks = null;
    }

    /* Helper methods */

    @Override
    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, false);
    }

    @Override
    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = mCallbacks != null ? ((ModularActivity)mCallbacks).getSupportFragmentManager().beginTransaction() : null;
            if (fragmentTransaction != null) {
                fragmentTransaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_fade_in, R.anim.abc_fade_out);
                if (addToBackStack) fragmentTransaction.addToBackStack(Fragment.class.getSimpleName());
                replaceFragment(fragment, fragmentTransaction);
            }
        }
    }

    @Override
    public void replaceFragment(Fragment fragment, FragmentTransaction fragmentTransaction) {
        if (fragment != null && fragmentTransaction != null) {
            try {
                fragmentTransaction.replace(mContentFragmentView.getId(), fragment).commit();
            } catch (IllegalStateException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    @Override
    public void removeFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = mCallbacks != null ? ((ModularActivity)mCallbacks).getSupportFragmentManager().beginTransaction() : null;
            if (fragmentTransaction != null) {
                fragmentTransaction.remove(fragment).commit();
            }
        }
    }

    @Override
    public View getFragmentView() {
        return mContentFragmentView;
    }

    @Override
    public Fragment getFragment() {
        Fragment fragment = null;
        if (mContentFragmentView != null && mCallbacks != null) {
            fragment = ((ModularActivity) mCallbacks).getSupportFragmentManager().findFragmentById(mContentFragmentView.getId());
        }
        return fragment;
    }

}
