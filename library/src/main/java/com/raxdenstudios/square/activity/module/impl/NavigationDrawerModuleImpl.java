package com.raxdenstudios.square.activity.module.impl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.NavigationDrawerModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;

/**
 * Created by agomez on 21/05/2015.
 */
public class NavigationDrawerModuleImpl extends ModuleActivityImpl implements NavigationDrawerModule.NavigationDrawerModuleListener {

    private static final String TAG = NavigationDrawerModuleImpl.class.getSimpleName();

    private View mContentDrawerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private NavigationDrawerModule mCallbacks;

    public NavigationDrawerModuleImpl(ModularActivity activity) {
        if (!(activity instanceof NavigationDrawerModule)) {
            throw new IllegalStateException("Activity must implement NavigationDrawerModule.");
        }
        mCallbacks = (NavigationDrawerModule)activity;
    }

    @Override
    public void onModuleConfigurationChanged(Context context, Configuration configuration) {
        if (mDrawerToggle != null) mDrawerToggle.onConfigurationChanged(configuration);
    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);
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
                        public void onClick(View v) {
                            if (mDrawerToggle != null && !mDrawerToggle.isDrawerIndicatorEnabled()) {
                                if (mCallbacks != null) ((ModularActivity)mCallbacks).onBackPressed();
                            }
                        }
                    });
                    mDrawerLayout.setDrawerListener(mDrawerToggle);
                }
                if (savedInstanceState == null) {
                    replaceFragment(mCallbacks.initContentDrawerFragment());
                }
            }
        }
        mCallbacks.onModuleLoaded(this);
    }

    @Override
    public void onModulePostCreate(Context context, Bundle savedInstanceState) {
        super.onModulePostCreate(context, savedInstanceState);

        if (mDrawerToggle != null) mDrawerToggle.syncState();
    }

    @Override
    public void onModulePrepareOptionsMenu(Context context, Menu menu) {
        if (mCallbacks != null && mDrawerToggle != null && mDrawerLayout != null) {
            if (((ModularActivity)mCallbacks).getSupportFragmentManager().getBackStackEntryCount() > 0) {
                mDrawerToggle.setDrawerIndicatorEnabled(false);
                mDrawerToggle.setHomeAsUpIndicator(((ModularActivity)mCallbacks).getDrawerToggleDelegate().getThemeUpIndicator());
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            } else {
                mDrawerToggle.setDrawerIndicatorEnabled(true);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        }
    }

    @Override
    public boolean onModuleBackPressed(Context context) {
        if (isOpen()) {
            close();
            return true;
        }
        return false;
    }

    @Override
    public void onModuleDestroy(Context context) {
        super.onModuleDestroy(context);
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
            ((ModularActivity)mCallbacks).runOnUiThread(new Runnable() {
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
            fragment = ((ModularActivity)mCallbacks).getSupportFragmentManager().findFragmentById(mContentDrawerView.getId());
        }
        return fragment;
    }

    private ActionBarDrawerToggle initActionBarDrawerToogle(Context context) {
        return new ActionBarDrawerToggle((Activity)context, mDrawerLayout, R.string.app__drawer_open, R.string.app__drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                NavigationDrawerModuleImpl.this.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                NavigationDrawerModuleImpl.this.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                NavigationDrawerModuleImpl.this.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
                NavigationDrawerModuleImpl.this.onDrawerStateChanged(newState);
            }
        };
    }

    private ActionBarDrawerToggle initActionBarDrawerToogle(Context context, Toolbar toolbar) {
        return new ActionBarDrawerToggle((Activity)context, mDrawerLayout, toolbar, R.string.app__drawer_open, R.string.app__drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                NavigationDrawerModuleImpl.this.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                NavigationDrawerModuleImpl.this.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                NavigationDrawerModuleImpl.this.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
                NavigationDrawerModuleImpl.this.onDrawerStateChanged(newState);
            }
        };
    }

    private void onDrawerClosed(View drawerView) {
        if (mCallbacks != null) ((ModularActivity)mCallbacks).supportInvalidateOptionsMenu();
        if (mCallbacks != null) mCallbacks.onDrawerClosed(drawerView);
    }

    private void onDrawerOpened(View drawerView) {
        if (mCallbacks != null) ((ModularActivity)mCallbacks).supportInvalidateOptionsMenu();
        if (mCallbacks != null) mCallbacks.onDrawerOpened(drawerView);
    }

    private void onDrawerSlide(View drawerView, float slideOffset) {
        if (mCallbacks != null) mCallbacks.onDrawerSlide(drawerView, slideOffset);
    }

    private void onDrawerStateChanged(int newState) {
        if (mCallbacks != null) mCallbacks.onDrawerStateChanged(newState);
    }

}
