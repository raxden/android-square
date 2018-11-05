package com.raxdenstudios.square.interceptor.commons.openhelper;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by agomez on 08/05/2015.
 */
public class OpenHelperActivityInterceptor<T extends SQLiteOpenHelper> extends ActivityInterceptor<OpenHelperInterceptorCallback<T>> implements OpenHelperInterceptor {

    private SQLiteOpenHelper mOpenHelper;

    public OpenHelperActivityInterceptor(@NonNull FragmentActivity activity) {
        super(activity, null);
    }

    public OpenHelperActivityInterceptor(@NonNull FragmentActivity activity, @NonNull OpenHelperInterceptorCallback<T> callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOpenHelper = getCallback().onCreateOpenHelper(getActivity(), savedInstanceState);
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
