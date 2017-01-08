package com.raxdenstudios.square.interceptor.type;

import android.app.Activity;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorInteractor;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an activity interceptor.
 */
public abstract class ActivitySimpleInterceptor
        extends ActivityInterceptor<InterceptorInteractor, InterceptorCallback<InterceptorInteractor>> {

    public ActivitySimpleInterceptor(Activity activity) {
        super(activity);
    }

}
