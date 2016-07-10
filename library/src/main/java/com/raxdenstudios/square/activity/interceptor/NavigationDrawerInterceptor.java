package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by agomez on 21/05/2015.
 */
public interface NavigationDrawerInterceptor {

    void onInterceptorLoaded(NavigationDrawerInterceptorListener interceptor);

    View onCreateContentDrawerView(Bundle savedInstanceState);
    DrawerLayout onCreateDrawerLayout(Bundle savedInstanceState);
    Toolbar onCreateToolbarView(Bundle savedInstanceState);
    Fragment initContentDrawerFragment();

    void onDrawerClosed(View drawerView);
    void onDrawerOpened(View drawerView);
    void onDrawerSlide(View drawerView, float slideOffset);
    void onDrawerStateChanged(int newState);

    interface NavigationDrawerInterceptorListener {
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
