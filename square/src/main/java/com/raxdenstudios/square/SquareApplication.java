package com.raxdenstudios.square;

import android.app.Application;
import android.content.res.Configuration;

import com.raxdenstudios.square.interceptor.ApplicationInterceptor;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.manager.ApplicationInterceptorManager;
import com.raxdenstudios.square.manager.InterceptorManagerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ángel Gómez
 * <p>
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

    protected abstract void setupInterceptors(List<Interceptor> interceptorList);

    private ApplicationInterceptorManager getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = (ApplicationInterceptorManager) InterceptorManagerFactory.buildManager(this);
            List<Interceptor> interceptorList = new ArrayList<>();
            setupInterceptors(interceptorList);
            for (Interceptor interceptor : interceptorList) {
                if (interceptor instanceof ApplicationInterceptor) {
                    mInterceptorManager.addInterceptor((ApplicationInterceptor) interceptor);
                }
            }
        }
        return mInterceptorManager;
    }

}
