package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.raxdenstudios.square.activity.interceptor.FragmentContentInterceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.FragmentContentInterceptorDelegate;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 02/06/2015.
 */
public class FragmentContentInterceptorImpl extends InterceptorActivityImpl
        implements FragmentContentInterceptorDelegate {

    private static String TAG = FragmentContentInterceptorImpl.class.getSimpleName();

    private FragmentContentInterceptor mCallbacks;
    private View mContentFragmentView;
    private int[] animations = new int[] {
            android.R.animator.fade_in,
            android.R.animator.fade_out,
            android.R.animator.fade_in,
            android.R.animator.fade_out
    };

    public FragmentContentInterceptorImpl(Activity activity) {
        super(activity);
        mCallbacks = (FragmentContentInterceptor)activity;
        mCallbacks.onInterceptorCreated(this);
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);
        mContentFragmentView = mCallbacks.onCreateContentFragmentView(savedInstanceState);
        if (savedInstanceState == null) {
            Fragment contentFragment = mCallbacks.onCreateContentFragment();
            replaceFragment(contentFragment);
            mCallbacks.onContentFragmentCreated(contentFragment);
        }
    }

    @Override
    public void onInterceptorDestroy() {
        super.onInterceptorDestroy();
        mCallbacks = null;
    }

    @Override
    public int addFragment(Fragment fragment) {
        if (fragment != null) {
            return addFragment(fragment, animations);
        }
        return 0;
    }

    @Override
    public int addFragment(Fragment fragment, int[] animations) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            setAnimations(fragmentTransaction, animations);
            return addFragment(fragment, fragmentTransaction);
        }
        return 0;
    }

    @Override
    public int addFragment(Fragment fragment, FragmentTransaction fragmentTransaction) {
        if (fragment != null && fragmentTransaction != null) {
            fragmentTransaction = fragmentTransaction.add(getFragmentView().getId(), fragment);
            return performTransaction(fragmentTransaction);
        }
        return 0;
    }

    @Override
    public int replaceFragment(Fragment fragment) {
        if (fragment != null) {
            return replaceFragment(fragment, false);
        }
        return 0;
    }

    @Override
    public int replaceFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment != null) {
            return replaceFragment(fragment, addToBackStack, animations);
        }
        return 0;
    }

    @Override
    public int replaceFragment(Fragment fragment, boolean addToBackStack, int[] animations) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            if (fragmentTransaction != null) {
                setAnimations(fragmentTransaction, animations);
                if (addToBackStack)
                    fragmentTransaction.addToBackStack(Fragment.class.getSimpleName());
                return replaceFragment(fragment, fragmentTransaction);
            }
        }
        return 0;
    }

    @Override
    public int replaceFragment(Fragment fragment, FragmentTransaction fragmentTransaction) {
        if (fragment != null && fragmentTransaction != null) {
            fragmentTransaction = fragmentTransaction.replace(getFragmentView().getId(), fragment);
            return performTransaction(fragmentTransaction);
        }
        return 0;
    }

    @Override
    public int removeFragment(Fragment fragment) {
        if (fragment != null) {
            return removeFragment(fragment, getFragmentTransaction());
        }
        return 0;
    }

    @Override
    public int removeFragment(Fragment fragment, FragmentTransaction fragmentTransaction) {
        if (fragment != null && fragmentTransaction != null) {
            fragmentTransaction = fragmentTransaction.remove(fragment);
            fragmentTransaction.addToBackStack(null);
            return performTransaction(fragmentTransaction);
        }
        return 0;
    }

    @Override
    public View getFragmentView() {
        return mContentFragmentView;
    }

    @Override
    public Fragment getFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        if (getFragmentView() != null && fragmentManager != null) {
            return fragmentManager.findFragmentById(getFragmentView().getId());
        }
        return null;
    }

    private void setAnimations(FragmentTransaction fragmentTransaction, int[] animations) {
        if (fragmentTransaction != null) {
            if (animations.length == 2) {
                fragmentTransaction.setCustomAnimations(animations[0], animations[1]);
            } else if (animations.length == 4) {
                fragmentTransaction.setCustomAnimations(animations[0], animations[1], animations[2], animations[3]);
            }
        }
    }

    private int performTransaction(FragmentTransaction fragmentTransaction) {
        try {
            return fragmentTransaction.commit();
        } catch (IllegalStateException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return 0;
    }

}
