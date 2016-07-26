package com.raxdenstudios.square.activity.interceptor;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.NavigationDrawerInterceptorDelegate;

/**
 * Created by agomez on 21/05/2015.
 */
public interface NavigationDrawerInterceptor<T extends Fragment> extends Interceptor {

    void onInterceptorCreated(NavigationDrawerInterceptorDelegate callback);

    View onCreateContentDrawerView(Bundle savedInstanceState);

    void onContentDrawerViewCreated(View view, Bundle savedInstanceState);

    DrawerLayout onCreateDrawerLayout(Bundle savedInstanceState);

    void onDrawerLayoutCreated(DrawerLayout drawerLayout, Bundle savedInstanceState);

    Toolbar onCreateToolbarView(Bundle savedInstanceState);

    void onToolbarViewCreated(Toolbar toolbar, Bundle savedInstanceState);

    void onActionBarDrawerToggleCreated(ActionBarDrawerToggle drawerToggle, Bundle savedInstanceState);

    T onCreateContentDrawerFragment();

    void onContentDrawerFragmentCreated(T fragment);

    void onDrawerClosed(View drawerView);

    void onDrawerOpened(View drawerView);

    void onDrawerSlide(View drawerView, float slideOffset);

    void onDrawerStateChanged(int newState);

}
