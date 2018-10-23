package com.raxdenstudios.square;

import android.content.res.Configuration;

import com.raxdenstudios.square.interceptor.ApplicationInterceptor;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.manager.ApplicationMultiDexInterceptorManager;
import com.raxdenstudios.square.manager.InterceptorManagerFactory;

import java.util.ArrayList;
import java.util.List;

import androidx.multidex.MultiDexApplication;

/**
 * Created by Ángel Gómez
 *
 * SquareMultiDexApplication is an abstract class that adds interceptor functionality to the
 * MultiDexApplication.
 */
public abstract class SquareMultiDexApplication extends MultiDexApplication {

    private ApplicationMultiDexInterceptorManager mInterceptorManager;

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

    private ApplicationMultiDexInterceptorManager getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = (ApplicationMultiDexInterceptorManager) InterceptorManagerFactory.buildManager(this);
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
