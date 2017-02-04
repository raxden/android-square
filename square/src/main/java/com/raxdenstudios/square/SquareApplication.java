package com.raxdenstudios.square;

import android.app.Application;
import android.content.res.Configuration;

import com.raxdenstudios.square.interceptor.ApplicationInterceptor;
import com.raxdenstudios.square.manager.ApplicationInterceptorManager;
import com.raxdenstudios.square.manager.InterceptorManagerFactory;

import java.util.List;

/**
 * Created by Ángel Gómez
 *
 * SquareApplication is an abstract class that adds interceptor functionality to the application.
 */
public abstract class SquareApplication extends Application {

    private ApplicationInterceptorManager mInterceptorManager;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getInterceptorManager().onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getInterceptorManager().onCreate();
    }

    /* Support methods */

    private ApplicationInterceptorManager getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = (ApplicationInterceptorManager) InterceptorManagerFactory.buildManager(this);
            addInterceptor(mInterceptorManager.getInterceptors());
        }
        return mInterceptorManager;
    }

    protected void addInterceptor(List<ApplicationInterceptor> interceptors) {

    }

}
