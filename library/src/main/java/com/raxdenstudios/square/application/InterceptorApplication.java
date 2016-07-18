package com.raxdenstudios.square.application;

import android.app.Application;
import android.content.res.Configuration;

import com.raxdenstudios.square.application.interceptor.manager.IInterceptorApplication;
import com.raxdenstudios.square.application.interceptor.manager.InterceptorApplicationManager;

import java.util.List;

/**
 * Created by agomez on 13/07/2015.
 */
public class InterceptorApplication extends Application {

    /* Interceptor Manager */
    private InterceptorApplicationManager mInterceptorManager;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getInterceptorManager().onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getInterceptorManager().onCreateInterceptors();
    }

    private InterceptorApplicationManager getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = new InterceptorApplicationManager(this);
            addInterceptor(mInterceptorManager.getInterceptors());
        }
        return mInterceptorManager;
    }

    protected void addInterceptor(List<IInterceptorApplication> interceptors) {

    }

}
