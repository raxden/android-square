package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.activity.InterceptorActivity;
import com.raxdenstudios.square.activity.interceptor.NavigationDrawerInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 21/05/2015.
 */
public class NavigationDrawerInterceptorImpl extends InterceptorActivityImpl implements NavigationDrawerInterceptor.NavigationDrawerInterceptorCallback {

    private static final String TAG = NavigationDrawerInterceptorImpl.class.getSimpleName();

    private View mContentDrawerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private NavigationDrawerInterceptor mCallbacks;

    public NavigationDrawerInterceptorImpl(InterceptorActivity activity) {
        if (!(activity instanceof NavigationDrawerInterceptor)) {
            throw new IllegalStateException("Activity must implement NavigationDrawerInterceptor.");
        }
        mCallbacks = (NavigationDrawerInterceptor)activity;
    }

    @Override
    public void onInterceptorConfigurationChanged(Context context, Configuration configuration) {
        if (mDrawerToggle != null) mDrawerToggle.onConfigurationChanged(configuration);
    }

    @Override
    public void onInterceptorCreate(Context context, Bundle savedInstanceState) {
        super.onInterceptorCreate(context, savedInstanceState);
        mContentDrawerView = mCallbacks.onCreateContentDrawerView(savedInstanceState);
        if (mContentDrawerView != null) {
            mDrawerLayout = mCallbacks.onCreateDrawerLayout(savedInstanceState);
            mToolbar = mCallbacks.onCreateToolbarView(savedInstanceState);
            if (mDrawerLayout != null) {
                // set a custom shadow that overlays the main content when the drawer opens
                mDrawerLayout.setDrawerShadow(R.drawable.app__drawer_shadow, GravityCompat.START);
                // ActionBarDrawerToggle ties together the the proper interactions between the sliding drawer and the action bar app icon
                mDrawerToggle = mToolbar != null ? initActionBarDrawerToogle(context, mToolbar) : initActionBarDrawerToogle(context);
                if (mDrawerToggle != null) {
                    mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mDrawerToggle != null && !mDrawerToggle.isDrawerIndicatorEnabled()) {
                                if (mCallbacks != null) ((InterceptorActivity)mCallbacks).onBackPressed();
                            }
                        }
                    });
                    mDrawerLayout.addDrawerListener(mDrawerToggle);
                }
                if (savedInstanceState == null) {
                    replaceFragment(mCallbacks.initContentDrawerFragment());
                }
            }
        }
        mCallbacks.onInterceptorLoaded(this);
    }

    @Override
    public void onInterceptorPostCreate(Context context, Bundle savedInstanceState) {
        super.onInterceptorPostCreate(context, savedInstanceState);

        if (mDrawerToggle != null) mDrawerToggle.syncState();
    }

    @Override
    public void onInterceptorPrepareOptionsMenu(Context context, Menu menu) {
        if (mCallbacks != null && mDrawerToggle != null && mDrawerLayout != null) {
            if (((InterceptorActivity)mCallbacks).getFragmentManager().getBackStackEntryCount() > 0) {
                mDrawerToggle.setDrawerIndicatorEnabled(false);
                mDrawerToggle.setHomeAsUpIndicator(((InterceptorActivity)mCallbacks).getDrawerToggleDelegate().getThemeUpIndicator());
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            } else {
                mDrawerToggle.setDrawerIndicatorEnabled(true);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        }
    }

    @Override
    public boolean onInterceptorBackPressed(Context context) {
        if (isOpen()) {
            close();
            return true;
        }
        return false;
    }

    @Override
    public void onInterceptorDestroy(Context context) {
        super.onInterceptorDestroy(context);
        mCallbacks = null;
    }

    @Override
    public void open() {
        if (mDrawerLayout != null && mContentDrawerView != null) {
            mDrawerLayout.openDrawer(mContentDrawerView);
        }
    }

    @Override
    public void close() {
        if (mCallbacks != null) {
            ((InterceptorActivity)mCallbacks).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mDrawerLayout != null && mContentDrawerView != null) {
                        mDrawerLayout.closeDrawer(mContentDrawerView);
                    }
                }
            });
        }
    }

    @Override
    public boolean isOpen() {
        if (mDrawerLayout != null && mContentDrawerView != null) {
            return mDrawerLayout.isDrawerOpen(mContentDrawerView);
        }
        return false;
    }


    @Override
    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, false);
    }

    @Override
    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = mCallbacks != null ? ((InterceptorActivity)mCallbacks).getFragmentManager().beginTransaction() : null;
            if (fragmentTransaction != null) {
                int fadeIn = android.R.animator.fade_in;
                int fadeOut = android.R.animator.fade_out;
                fragmentTransaction.setCustomAnimations(fadeIn, fadeOut, fadeIn, fadeOut);
                if (addToBackStack) fragmentTransaction.addToBackStack(Fragment.class.getSimpleName());
                replaceFragment(fragment, fragmentTransaction);
            }
        }
    }

    @Override
    public void replaceFragment(Fragment fragment, FragmentTransaction fragmentTransaction) {
        if (fragment != null && fragmentTransaction != null) {
            fragmentTransaction.replace(mContentDrawerView.getId(), fragment).commit();
        }
    }

    @Override
    public View getFragmentView() {
        return mContentDrawerView;
    }

    @Override
    public View getDrawerLayout() {
        return mDrawerLayout;
    }

    public Fragment getFragment() {
        Fragment fragment = null;
        if (mContentDrawerView != null && mCallbacks != null) {
            fragment = ((InterceptorActivity)mCallbacks).getFragmentManager().findFragmentById(mContentDrawerView.getId());
        }
        return fragment;
    }

    private ActionBarDrawerToggle initActionBarDrawerToogle(Context context) {
        return new ActionBarDrawerToggle((Activity)context, mDrawerLayout, R.string.app__drawer_open, R.string.app__drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                NavigationDrawerInterceptorImpl.this.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                NavigationDrawerInterceptorImpl.this.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                NavigationDrawerInterceptorImpl.this.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
                NavigationDrawerInterceptorImpl.this.onDrawerStateChanged(newState);
            }
        };
    }

    private ActionBarDrawerToggle initActionBarDrawerToogle(Context context, Toolbar toolbar) {
        return new ActionBarDrawerToggle((Activity)context, mDrawerLayout, toolbar, R.string.app__drawer_open, R.string.app__drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                NavigationDrawerInterceptorImpl.this.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                NavigationDrawerInterceptorImpl.this.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                NavigationDrawerInterceptorImpl.this.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
                NavigationDrawerInterceptorImpl.this.onDrawerStateChanged(newState);
            }
        };
    }

    private void onDrawerClosed(View drawerView) {
        if (mCallbacks != null) ((InterceptorActivity)mCallbacks).supportInvalidateOptionsMenu();
        if (mCallbacks != null) mCallbacks.onDrawerClosed(drawerView);
    }

    private void onDrawerOpened(View drawerView) {
        if (mCallbacks != null) ((InterceptorActivity)mCallbacks).supportInvalidateOptionsMenu();
        if (mCallbacks != null) mCallbacks.onDrawerOpened(drawerView);
    }

    private void onDrawerSlide(View drawerView, float slideOffset) {
        if (mCallbacks != null) mCallbacks.onDrawerSlide(drawerView, slideOffset);
    }

    private void onDrawerStateChanged(int newState) {
        if (mCallbacks != null) mCallbacks.onDrawerStateChanged(newState);
    }

}
