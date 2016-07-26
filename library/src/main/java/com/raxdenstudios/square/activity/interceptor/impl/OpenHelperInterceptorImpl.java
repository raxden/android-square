package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.raxdenstudios.square.activity.interceptor.OpenHelperInterceptor;
import com.raxdenstudios.square.activity.interceptor.callback.OpenHelperInterceptorCallback;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 08/05/2015.
 */
public class OpenHelperInterceptorImpl extends InterceptorActivityImpl<OpenHelperInterceptor>
        implements OpenHelperInterceptorCallback {

    private static final String TAG = OpenHelperInterceptorImpl.class.getSimpleName();

    private SQLiteOpenHelper mOpenHelper;

    public OpenHelperInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);

        mOpenHelper = mCallbacks.initOpenHelper(getContext(), savedInstanceState);
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
