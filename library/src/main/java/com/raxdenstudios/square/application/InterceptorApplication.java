package com.raxdenstudios.square.application;

import android.app.Application;
import android.content.res.Configuration;

import com.raxdenstudios.square.application.interceptor.manager.InterceptorApplicationManager;

/**
 * Created by agomez on 13/07/2015.
 */
public class InterceptorApplication extends Application {

    /* Interceptor Manager */
    private InterceptorApplicationManager mInterceptorManager;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getInterceptorManager().onConfigurationChanged(getApplicationContext(), newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getInterceptorManager().onCreateInterceptors(getApplicationContext());
    }

    private InterceptorApplicationManager getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = new InterceptorApplicationManager(this);
            initInterceptors(mInterceptorManager);
        }
        return mInterceptorManager;
    }

    protected void initInterceptors(InterceptorApplicationManager interceptorManager) {

    }

}
