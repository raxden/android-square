package com.raxdenstudios.square;

import android.app.Application;
import android.content.res.Configuration;

import com.raxdenstudios.square.interceptor.type.ApplicationInterceptor;
import com.raxdenstudios.square.interceptor.manager.ApplicationInterceptorManager;
import com.raxdenstudios.square.interceptor.manager.InterceptorManagerFactory;

import java.util.List;

/**
 * Created by Ángel Gómez on 18/12/2016.
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
            addCustomInterceptorToStack(mInterceptorManager.getInterceptors());
        }
        return mInterceptorManager;
    }

    protected void addCustomInterceptorToStack(List<ApplicationInterceptor> interceptors) {

    }

}
