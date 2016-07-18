package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
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
        super(activity);
        if (!(activity instanceof OpenHelperInterceptor)) {
            throw new IllegalStateException("Activity must implement OpenHelperInterceptor.");
        }
        mCallbacks = (OpenHelperInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Bundle bundle) {
        super.onInterceptorCreate(bundle);

        if (mCallbacks != null) {
            mOpenHelper = mCallbacks.initOpenHelper(getContext(), bundle);
        }
    }

    @Override
    public void onInterceptorDestroy() {
        super.onInterceptorDestroy();

        if (mOpenHelper != null) {
            mOpenHelper.close();
            mOpenHelper = null;
        }
    }
}
