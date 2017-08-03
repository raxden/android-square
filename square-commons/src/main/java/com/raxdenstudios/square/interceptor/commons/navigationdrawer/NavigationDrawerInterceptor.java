package com.raxdenstudios.square.interceptor.commons.navigationdrawer;

import com.raxdenstudios.square.interceptor.Interceptor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface NavigationDrawerInterceptor extends Interceptor {

    void toggleDrawer();

    void openDrawer();

    void closeDrawer();

    boolean isOpenDrawer();

}
