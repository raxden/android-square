package com.raxdenstudios.square.interceptor.commons.navigationcontentdrawer;

import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

/**
 * Created by agomez on 21/05/2015.
 */
public interface NavigationContentDrawerInterceptorCallback<T extends Fragment> extends InterceptorCallback {

    View onCreateContentDrawerView(Bundle savedInstanceState);

    DrawerLayout onCreateDrawerLayout(Bundle savedInstanceState);

    void onDrawerLayoutCreated(DrawerLayout drawerLayout);

    Toolbar onCreateToolbarView(Bundle savedInstanceState);

    void onActionBarDrawerToggleCreated(ActionBarDrawerToggle drawerToggle);

    T onCreateContentDrawerFragment();

    void onContentDrawerFragmentLoaded(T fragment);

    void onDrawerClosed(View drawerView);

    void onDrawerOpened(View drawerView);

    void onDrawerSlide(View drawerView, float slideOffset);

    void onDrawerStateChanged(int newState);

}
