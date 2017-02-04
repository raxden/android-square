package com.raxdenstudios.square.interceptor.fullscreen;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by Ángel Gómez on 26/12/2016.
 */
public class FullScreenActivityInterceptor
        extends ActivityInterceptor<FullScreenInteractor, FullScreenInterceptorCallback>
        implements FullScreenInteractor {

    public FullScreenActivityInterceptor(Activity activity, FullScreenInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        int mask = WindowManager.LayoutParams.FLAG_FULLSCREEN;

        mActivity.getWindow().setFlags(flags, mask);
    }

}
