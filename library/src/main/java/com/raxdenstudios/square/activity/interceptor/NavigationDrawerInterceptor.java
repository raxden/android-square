package com.raxdenstudios.square.activity.interceptor;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.raxdenstudios.square.Interceptor;

/**
 * Created by agomez on 21/05/2015.
 */
public interface NavigationDrawerInterceptor extends Interceptor {

    View onCreateContentDrawerView(Bundle savedInstanceState);

    DrawerLayout onCreateDrawerLayout(Bundle savedInstanceState);

    Toolbar onCreateToolbarView(Bundle savedInstanceState);

    Fragment initContentDrawerFragment();

    void onDrawerClosed(View drawerView);

    void onDrawerOpened(View drawerView);

    void onDrawerSlide(View drawerView, float slideOffset);

    void onDrawerStateChanged(int newState);

    interface NavigationDrawerInterceptorCallback {
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
