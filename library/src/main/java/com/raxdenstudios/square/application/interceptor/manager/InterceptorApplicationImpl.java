package com.raxdenstudios.square.application.interceptor.manager;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.InterceptorCallback;
import com.raxdenstudios.square.application.InterceptorApplication;

/**
 * Created by agomez on 13/07/2015.
 */
public class InterceptorApplicationImpl<I extends Interceptor>
        implements IInterceptorApplication, InterceptorCallback {

    private static final String TAG = InterceptorApplicationImpl.class.getSimpleName();

    protected Application mApplication;
    protected I mCallbacks;

    @SuppressWarnings("unchecked")
    public InterceptorApplicationImpl(Application application) {
        if (!(application instanceof InterceptorApplication)) {
            throw new IllegalStateException(this.getClass().getSimpleName()+" interceptor must be used just on IInterceptorApplication");
        }
        mApplication = application;

        mCallbacks = (I)application;
        mCallbacks.onInterceptorCreated(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public void onCreate() {

    }

    public Context getContext() {
        return mApplication.getApplicationContext();
    }

}
