package com.raxdenstudios.square.manager;

import android.app.Application;
import android.content.res.Configuration;

import com.raxdenstudios.square.interceptor.ApplicationInterceptor;
import com.raxdenstudios.square.lifecycle.ApplicationLifecycle;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

public class ApplicationInterceptorManager extends InterceptorManager<Application, ApplicationInterceptor> implements ApplicationLifecycle {

    public ApplicationInterceptorManager(Application application) {
        super(application);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        for (ApplicationInterceptor interceptor : interceptors) {
            interceptor.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onCreate() {
        for (ApplicationInterceptor interceptor : interceptors) {
            interceptor.onCreate();
        }
    }

    @Override
    public void onLowMemory() {
        for (ApplicationInterceptor interceptor : interceptors) {
            interceptor.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        for (ApplicationInterceptor interceptor : interceptors) {
            interceptor.onTrimMemory(level);
        }
    }

}
