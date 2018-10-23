package com.raxdenstudios.square.interceptor;

import androidx.fragment.app.FragmentActivity;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an activity interceptor.
 */
public abstract class ActivitySimpleInterceptor extends ActivityInterceptor<InterceptorCallback> {

    public ActivitySimpleInterceptor(FragmentActivity activity) {
        super(activity);
    }

}