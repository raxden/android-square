package com.raxdenstudios.square.application.interceptor.manager;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.raxdenstudios.square.application.InterceptorMultiDexApplication;
import com.raxdenstudios.square.application.interceptor.LocaleInterceptor;
import com.raxdenstudios.square.application.interceptor.impl.LocaleInterceptorImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agomez on 13/07/2015.
 */
public class InterceptorApplicationManager {

    private static final String TAG = InterceptorApplicationManager.class.getSimpleName();

    protected List<InterceptorApplication> interceptors;

    public InterceptorApplicationManager(com.raxdenstudios.square.application.InterceptorApplication application) {
        if (application != null) initInterceptors(application);
    }

    public InterceptorApplicationManager(InterceptorMultiDexApplication application) {
        if (application != null) initInterceptors(application);
    }

    public void onConfigurationChanged(Context context, Configuration newConfig) {
        if (interceptors != null) {
            for (InterceptorApplication interceptor : interceptors) {
                interceptor.onConfigurationChanged(context, newConfig);
            }
        }
    }

    public void onCreateInterceptors(Context context) {
        if (interceptors != null) {
            for (InterceptorApplication interceptor : interceptors) {
                interceptor.onCreate(context);
            }
        }
    }

    public void addInterceptor(InterceptorApplication interceptor) {
        interceptors.add(interceptor);
    }

    public List<InterceptorApplication> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<InterceptorApplication> interceptors) {
        this.interceptors = interceptors;
    }


    private void initInterceptors(com.raxdenstudios.square.application.InterceptorApplication application) {
        Log.d(TAG, "========== Prepare to init application interceptors ==========");
        interceptors = new ArrayList<>();
        if (application instanceof LocaleInterceptor) {
            Log.d(TAG, "....."+LocaleInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new LocaleInterceptorImpl(application));
        }
        Log.d(TAG, "=========================================================");
    }

    private void initInterceptors(InterceptorMultiDexApplication application) {
        Log.d(TAG, "========== Prepare to init application interceptors ==========");
        interceptors = new ArrayList<>();
        if (application instanceof LocaleInterceptor) {
            Log.d(TAG, "....."+LocaleInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new LocaleInterceptorImpl(application));
        }
        Log.d(TAG, "=========================================================");
    }

}
