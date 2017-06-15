package com.raxdenstudios.square.interceptor.openhelper;

import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by agomez on 08/05/2015.
 */
public class OpenHelperActivityInterceptorImpl<T extends SQLiteOpenHelper> extends ActivityInterceptor<OpenHelperInterceptorCallback<T>> implements OpenHelperInterceptor {

    private SQLiteOpenHelper mOpenHelper;

    public OpenHelperActivityInterceptorImpl(@NonNull Activity activity) {
        super(activity);
    }

    public OpenHelperActivityInterceptorImpl(@NonNull Activity activity, @NonNull OpenHelperInterceptorCallback<T> callback) {
        super(activity, callback);
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
