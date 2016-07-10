package com.raxdenstudios.square.activity.interceptor.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.activity.InterceptorActivity;
import com.raxdenstudios.square.activity.interceptor.FragmentContentInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 02/06/2015.
 */
public class FragmentContentInterceptorImpl extends InterceptorActivityImpl implements FragmentContentInterceptor.FragmentContentInterceptorListener {

    private static String TAG = FragmentContentInterceptorImpl.class.getSimpleName();

    private View mContentFragmentView;
    private FragmentContentInterceptor mCallbacks;

    public FragmentContentInterceptorImpl(InterceptorActivity activity) {
        if (!(activity instanceof FragmentContentInterceptor)) {
            throw new IllegalStateException("Activity must implement FragmentContentInterceptor.");
        }
        mCallbacks = (FragmentContentInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Context context, Bundle savedInstanceState) {
        super.onInterceptorCreate(context, savedInstanceState);
        mContentFragmentView = mCallbacks.onCreateContentFragmentView(savedInstanceState);
        if (mContentFragmentView != null && savedInstanceState == null) {
            replaceFragment(mCallbacks.initContentFragment());
        }
        mCallbacks.onInterceptorLoaded(this);
    }

    @Override
    public void onInterceptorDestroy(Context context) {
        super.onInterceptorDestroy(context);
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
            FragmentTransaction fragmentTransaction = mCallbacks != null ? ((InterceptorActivity)mCallbacks).getSupportFragmentManager().beginTransaction() : null;
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
            FragmentTransaction fragmentTransaction = mCallbacks != null ? ((InterceptorActivity)mCallbacks).getSupportFragmentManager().beginTransaction() : null;
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
            fragment = ((InterceptorActivity) mCallbacks).getSupportFragmentManager().findFragmentById(mContentFragmentView.getId());
        }
        return fragment;
    }

}
