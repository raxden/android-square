package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.activity.interceptor.NavigationDrawerInterceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.NavigationDrawerInterceptorDelegate;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 21/05/2015.
 */
public class NavigationDrawerInterceptorImpl extends InterceptorActivityImpl
        implements NavigationDrawerInterceptorDelegate {

    private static final String TAG = NavigationDrawerInterceptorImpl.class.getSimpleName();

    private NavigationDrawerInterceptor mCallbacks;
    private AppCompatActivity mCompatActivity;
    private View mContentDrawerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private int[] animations = new int[] {
            android.R.animator.fade_in,
            android.R.animator.fade_out,
            android.R.animator.fade_in,
            android.R.animator.fade_out
    };

    public NavigationDrawerInterceptorImpl(Activity activity) {
        super(activity);
        mCallbacks.onInterceptorCreated(this);
        mCallbacks = (NavigationDrawerInterceptor)activity;
        mCompatActivity = (AppCompatActivity)activity;
    }

    @Override
    public void onInterceptorConfigurationChanged(Configuration configuration) {
        if (mDrawerToggle != null) mDrawerToggle.onConfigurationChanged(configuration);
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);

        mContentDrawerView = mCallbacks.onCreateContentDrawerView(savedInstanceState);
        if (mContentDrawerView != null) {
            mDrawerLayout = mCallbacks.onCreateDrawerLayout(savedInstanceState);
            mToolbar = mCallbacks.onCreateToolbarView(savedInstanceState);
            if (mDrawerLayout != null) {
                // set a custom shadow that overlays the main content when the drawer opens
                mDrawerLayout.setDrawerShadow(R.drawable.app__drawer_shadow, GravityCompat.START);
                // ActionBarDrawerToggle ties together the the proper interactions between the sliding drawer and the action bar app icon
                mDrawerToggle = mToolbar != null ? initActionBarDrawerToogle(mToolbar) : initActionBarDrawerToogle();
                if (mDrawerToggle != null) {
                    mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mDrawerToggle != null && !mDrawerToggle.isDrawerIndicatorEnabled()) {
                                mCompatActivity.onBackPressed();
                            }
                        }
                    });
                    mDrawerLayout.addDrawerListener(mDrawerToggle);
                    mCallbacks.onActionBarDrawerToggleCreated(mDrawerToggle, savedInstanceState);
                }
            }
            if (savedInstanceState == null) {
                Fragment contentFragment = mCallbacks.onCreateContentDrawerFragment();
                replaceFragment(contentFragment);
                mCallbacks.onContentDrawerFragmentCreated(contentFragment);
            }
            mCallbacks.onToolbarViewCreated(mToolbar, savedInstanceState);
            mCallbacks.onDrawerLayoutCreated(mDrawerLayout, savedInstanceState);
            mCallbacks.onContentDrawerViewCreated(mContentDrawerView, savedInstanceState);
        }
    }

    @Override
    public void onInterceptorPostCreate(Bundle savedInstanceState) {
        super.onInterceptorPostCreate(savedInstanceState);

        if (mDrawerToggle != null) mDrawerToggle.syncState();
    }

    @Override
    public void onInterceptorPrepareOptionsMenu(Menu menu) {
        if (mCallbacks != null && mDrawerToggle != null && mDrawerLayout != null) {
            if (mCompatActivity.getFragmentManager().getBackStackEntryCount() > 0) {
                mDrawerToggle.setDrawerIndicatorEnabled(false);
                mDrawerToggle.setHomeAsUpIndicator(mCompatActivity.getDrawerToggleDelegate().getThemeUpIndicator());
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            } else {
                mDrawerToggle.setDrawerIndicatorEnabled(true);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        }
    }

    @Override
    public boolean onInterceptorBackPressed() {
        if (isOpen()) {
            close();
            return true;
        }
        return false;
    }

    @Override
    public void onInterceptorDestroy() {
        super.onInterceptorDestroy();
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
        mCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mDrawerLayout != null && mContentDrawerView != null) {
                    mDrawerLayout.closeDrawer(mContentDrawerView);
                }
            }
        });
    }

    @Override
    public boolean isOpen() {
        if (mDrawerLayout != null && mContentDrawerView != null) {
            return mDrawerLayout.isDrawerOpen(mContentDrawerView);
        }
        return false;
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
        return mContentDrawerView;
    }

    @Override
    public Fragment getFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        if (getFragmentView() != null && fragmentManager != null) {
            return fragmentManager.findFragmentById(getFragmentView().getId());
        }
        return null;
    }

    private ActionBarDrawerToggle initActionBarDrawerToogle() {
        return new ActionBarDrawerToggle(mCompatActivity, mDrawerLayout, R.string.app__drawer_open, R.string.app__drawer_close) {

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

    private ActionBarDrawerToggle initActionBarDrawerToogle(Toolbar toolbar) {
        return new ActionBarDrawerToggle(mCompatActivity, mDrawerLayout, toolbar, R.string.app__drawer_open, R.string.app__drawer_close) {

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
        if (mCallbacks != null) mCompatActivity.supportInvalidateOptionsMenu();
        if (mCallbacks != null) mCallbacks.onDrawerClosed(drawerView);
    }

    private void onDrawerOpened(View drawerView) {
        if (mCallbacks != null) mCompatActivity.supportInvalidateOptionsMenu();
        if (mCallbacks != null) mCallbacks.onDrawerOpened(drawerView);
    }

    private void onDrawerSlide(View drawerView, float slideOffset) {
        if (mCallbacks != null) mCallbacks.onDrawerSlide(drawerView, slideOffset);
    }

    private void onDrawerStateChanged(int newState) {
        if (mCallbacks != null) mCallbacks.onDrawerStateChanged(newState);
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
