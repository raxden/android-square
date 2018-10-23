package com.raxdenstudios.square.interceptor.commons.navigationdrawer;

import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

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
