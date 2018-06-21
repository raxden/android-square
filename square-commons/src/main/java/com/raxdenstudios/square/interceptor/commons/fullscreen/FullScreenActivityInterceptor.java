package com.raxdenstudios.square.interceptor.commons.fullscreen;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;

import com.raxdenstudios.square.interceptor.ActivitySimpleInterceptor;

/**
 * Created by Ángel Gómez on 26/12/2016.
 */
public class FullScreenActivityInterceptor extends ActivitySimpleInterceptor implements FullScreenInterceptor {

    public FullScreenActivityInterceptor(@NonNull Activity activity) {
        super(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        int mask = WindowManager.LayoutParams.FLAG_FULLSCREEN;

        mActivity.getWindow().setFlags(flags, mask);
    }

}
