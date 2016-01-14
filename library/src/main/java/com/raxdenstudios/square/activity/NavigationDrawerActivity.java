package com.raxdenstudios.square.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.activity.module.NavigationDrawerModule;

/**
 * Created by agomez on 19/02/2015.
 */
public abstract class NavigationDrawerActivity extends FragmentContentActivity
        implements NavigationDrawerModule {

    private static final String TAG = NavigationDrawerActivity.class.getSimpleName();

    private NavigationDrawerModuleListener mNavigationDrawerModule;

    @Override
    public void onModuleLoaded(NavigationDrawerModuleListener module) {
        mNavigationDrawerModule = module;
    }

    @Override
    public View onCreateContentDrawerView(Bundle savedInstanceState) {
        return findViewById(R.id.app__content_drawer);
    }

    @Override
    public DrawerLayout onCreateDrawerLayout(Bundle savedInstanceState) {
        return (DrawerLayout)findViewById(R.id.app__drawer_layout);
    }

    @Override
    public void onDrawerClosed(View drawerView) {}

    @Override
    public void onDrawerOpened(View drawerView) {}

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {}

    @Override
    public void onDrawerStateChanged(int newState) {}

    /* Getters && Setters */

    public NavigationDrawerModuleListener getNavigationDrawerModule() {
        return mNavigationDrawerModule;
    }

}
