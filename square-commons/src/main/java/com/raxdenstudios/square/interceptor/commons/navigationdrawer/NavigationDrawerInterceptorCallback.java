package com.raxdenstudios.square.interceptor.commons.navigationdrawer;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by agomez on 21/05/2015.
 */
public interface NavigationDrawerInterceptorCallback extends InterceptorCallback {

    View onCreateContentDrawerView(Bundle savedInstanceState);

    DrawerLayout onCreateDrawerLayout(Bundle savedInstanceState);

    void onDrawerLayoutCreated(DrawerLayout drawerLayout);

    Toolbar onCreateToolbarView(Bundle savedInstanceState);

    void onActionBarDrawerToggleCreated(ActionBarDrawerToggle drawerToggle);

    void onDrawerClosed(View drawerView);

    void onDrawerOpened(View drawerView);

    void onDrawerSlide(View drawerView, float slideOffset);

    void onDrawerStateChanged(int newState);

}