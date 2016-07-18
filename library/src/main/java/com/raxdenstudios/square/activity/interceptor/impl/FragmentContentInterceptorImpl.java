package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.raxdenstudios.square.activity.InterceptorActivity;
import com.raxdenstudios.square.activity.interceptor.FragmentContentInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 02/06/2015.
 */
public class FragmentContentInterceptorImpl extends InterceptorActivityImpl implements FragmentContentInterceptor.FragmentContentInterceptorCallback {

    private static String TAG = FragmentContentInterceptorImpl.class.getSimpleName();

    private View mContentFragmentView;
    private FragmentContentInterceptor mCallbacks;

    public FragmentContentInterceptorImpl(Activity activity) {
        super(activity);
        if (!(activity instanceof FragmentContentInterceptor)) {
            throw new IllegalStateException("Activity must implement FragmentContentInterceptor.");
        }
        mCallbacks = (FragmentContentInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);
        mContentFragmentView = mCallbacks.onCreateContentFragmentView(savedInstanceState);
        if (mContentFragmentView != null && savedInstanceState == null) {
            replaceFragment(mCallbacks.initContentFragment());
        }
    }

    @Override
    public void onInterceptorDestroy() {
        super.onInterceptorDestroy();
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
            FragmentTransaction fragmentTransaction = mCallbacks != null ? ((InterceptorActivity)mCallbacks).getFragmentManager().beginTransaction() : null;
            if (fragmentTransaction != null) {
                fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
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
            FragmentTransaction fragmentTransaction = mCallbacks != null ? ((InterceptorActivity)mCallbacks).getFragmentManager().beginTransaction() : null;
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
            fragment = ((InterceptorActivity) mCallbacks).getFragmentManager().findFragmentById(mContentFragmentView.getId());
        }
        return fragment;
    }

}
