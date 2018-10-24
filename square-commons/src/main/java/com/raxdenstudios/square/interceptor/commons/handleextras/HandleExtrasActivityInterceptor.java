package com.raxdenstudios.square.interceptor.commons.handleextras;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public class HandleExtrasActivityInterceptor extends ActivityInterceptor<HandleExtrasInterceptorCallback> implements HandleExtrasInterceptor {

    public HandleExtrasActivityInterceptor(@NonNull FragmentActivity activity) {
        super(activity);
    }

    public HandleExtrasActivityInterceptor(@NonNull FragmentActivity activity, @NonNull HandleExtrasInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = mActivity.getIntent() != null && mActivity.getIntent().getExtras() != null ? mActivity.getIntent().getExtras() : new Bundle();
        if (mCallback != null) {
            mCallback.onHandleExtras(savedInstanceState, extras);
        }
    }

}
