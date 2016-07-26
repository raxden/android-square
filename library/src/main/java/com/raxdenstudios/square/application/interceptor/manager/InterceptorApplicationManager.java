package com.raxdenstudios.square.application.interceptor.manager;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.raxdenstudios.square.application.interceptor.LocaleInterceptor;
import com.raxdenstudios.square.application.interceptor.TimberInterceptor;
import com.raxdenstudios.square.application.interceptor.impl.LocaleInterceptorImpl;
import com.raxdenstudios.square.application.interceptor.impl.TimberInterceptorImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agomez on 13/07/2015.
 */
public class InterceptorApplicationManager {

    private static final String TAG = InterceptorApplicationManager.class.getSimpleName();

    protected List<IInterceptorApplication> interceptors;

    public InterceptorApplicationManager(Application application) {
        if (application != null) initInterceptors(application);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (interceptors != null) {
            for (IInterceptorApplication interceptor : interceptors) {
                interceptor.onConfigurationChanged(newConfig);
            }
        }
    }

    public void onCreateInterceptors() {
        if (interceptors != null) {
            for (IInterceptorApplication interceptor : interceptors) {
                interceptor.onCreate();
            }
        }
    }

    public void addInterceptor(IInterceptorApplication interceptor) {
        interceptors.add(interceptor);
    }

    public List<IInterceptorApplication> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<IInterceptorApplication> interceptors) {
        this.interceptors = interceptors;
    }


    private void initInterceptors(Application application) {
        Log.d(TAG, "========== Prepare to init application interceptors ==========");
        interceptors = new ArrayList<>();
        if (application instanceof LocaleInterceptor) {
            Log.d(TAG, "....."+LocaleInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new LocaleInterceptorImpl(application));
        }
        if (application instanceof TimberInterceptor) {
            Log.d(TAG, "....."+TimberInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new TimberInterceptorImpl(application));
        }
        Log.d(TAG, "=========================================================");
    }

}
