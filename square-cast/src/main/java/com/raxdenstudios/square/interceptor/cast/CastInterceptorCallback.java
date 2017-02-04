package com.raxdenstudios.square.interceptor.cast;

import android.view.Menu;
import android.view.MenuItem;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public interface CastInterceptorCallback
        extends InterceptorCallback<CastInteractor> {

    /**
     *  return menu.findItem(R.id.media_route_menu_item)
     *
     *  ======== menu.xml =============================================================
     *  <menu xmlns:android="http://schemas.android.com/apk/res/android"
     *      xmlns:app="http://schemas.android.com/apk/res-auto">
     *
     *      <item
     *          android:id="@+id/media_route_menu_item"
     *          android:title="@string/media_route_menu_title"
     *          app:actionProviderClass="android.support.v7.app.MediaRouteActionProvider"
     *          app:showAsAction="always"/>
     *
     *  </menu>
     *  ===============================================================================
     * @param menu
     * @return menu.findItem(R.id.media_route_menu_item)
     */
    MenuItem onCreateMediaRouteMenuItem(Menu menu);

    void devicesNotAvailable();

    void devicesAvailable();

}
