package com.raxdenstudios.square.interceptor.commons.fullscreen;

import android.os.Bundle;
import android.view.WindowManager;

import com.raxdenstudios.square.interceptor.ActivitySimpleInterceptor;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

/**
 * Created by Ángel Gómez on 26/12/2016.
 */
public class FullScreenActivityInterceptor extends ActivitySimpleInterceptor implements FullScreenInterceptor {

    public FullScreenActivityInterceptor(@NonNull FragmentActivity activity) {
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
