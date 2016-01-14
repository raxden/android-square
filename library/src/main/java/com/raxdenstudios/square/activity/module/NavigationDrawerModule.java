package com.raxdenstudios.square.activity.module;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by agomez on 21/05/2015.
 */
public interface NavigationDrawerModule {

    void onModuleLoaded(NavigationDrawerModuleListener module);

    View onCreateContentDrawerView(Bundle savedInstanceState);
    DrawerLayout onCreateDrawerLayout(Bundle savedInstanceState);
    Toolbar onCreateToolbarView(Bundle savedInstanceState);
    Fragment initContentDrawerFragment();

    void onDrawerClosed(View drawerView);
    void onDrawerOpened(View drawerView);
    void onDrawerSlide(View drawerView, float slideOffset);
    void onDrawerStateChanged(int newState);

    interface NavigationDrawerModuleListener {
        void open();
        void close();
        boolean isOpen();
        void replaceFragment(Fragment fragment);
        void replaceFragment(Fragment fragment, boolean addToBackStack);
        void replaceFragment(Fragment fragment, FragmentTransaction fragmentTransaction);
        View getFragmentView();
        View getDrawerLayout();
        Fragment getFragment();
    }
}
