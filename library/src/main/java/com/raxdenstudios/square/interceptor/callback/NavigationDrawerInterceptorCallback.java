package com.raxdenstudios.square.interceptor.callback;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.NavigationDrawerInterceptorConfig;

/**
 * Created by agomez on 21/05/2015.
 */
public interface NavigationDrawerInterceptorCallback<T extends Fragment>
        extends InterceptorCallback<NavigationDrawerInterceptorConfig> {

    View onCreateContentDrawerView(Bundle savedInstanceState);

    DrawerLayout onCreateDrawerLayout(Bundle savedInstanceState);

    Toolbar onCreateToolbarView(Bundle savedInstanceState);

    void onActionBarDrawerToggleCreated(ActionBarDrawerToggle drawerToggle, Bundle savedInstanceState);

    T onCreateContentDrawerFragment();

    void onContentDrawerFragmentCreated(T fragment);

    void onDrawerClosed(View drawerView);

    void onDrawerOpened(View drawerView);

    void onDrawerSlide(View drawerView, float slideOffset);

    void onDrawerStateChanged(int newState);

}
