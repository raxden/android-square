package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.raxdenstudios.square.activity.interceptor.OpenHelperInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 08/05/2015.
 */
public class OpenHelperInterceptorImpl extends InterceptorActivityImpl implements OpenHelperInterceptor.OpenHelperInterfaceptorCallback {

    private static final String TAG = OpenHelperInterceptorImpl.class.getSimpleName();

    private OpenHelperInterceptor mCallbacks;
    private SQLiteOpenHelper mOpenHelper;

    public OpenHelperInterceptorImpl(Activity activity) {
        if (!(activity instanceof OpenHelperInterceptor)) {
            throw new IllegalStateException("Activity must implement OpenHelperInterceptor.");
        }
        mCallbacks = (OpenHelperInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Context context, Bundle bundle) {
        super.onInterceptorCreate(context, bundle);

        if (mCallbacks != null) {
            mOpenHelper = mCallbacks.initOpenHelper(context, bundle);
        }
    }

    @Override
    public void onInterceptorDestroy(Context context) {
        super.onInterceptorDestroy(context);

        if (mOpenHelper != null) {
            mOpenHelper.close();
            mOpenHelper = null;
        }
    }
}
