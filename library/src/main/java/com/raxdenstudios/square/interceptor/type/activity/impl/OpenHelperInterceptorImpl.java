package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.callback.OpenHelperInterceptorCallback;
import com.raxdenstudios.square.interceptor.config.OpenHelperInterceptorConfig;

/**
 * Created by agomez on 08/05/2015.
 */
public class OpenHelperInterceptorImpl<T extends SQLiteOpenHelper>
        extends ActivityInterceptor<OpenHelperInterceptorConfig, OpenHelperInterceptorCallback<T>>
        implements OpenHelperInterceptorConfig {

    private SQLiteOpenHelper mOpenHelper;

    public OpenHelperInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOpenHelper = mCallback.onCreateOpenHelper(mActivity, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mOpenHelper != null) {
            mOpenHelper.close();
            mOpenHelper = null;
        }
    }
}
