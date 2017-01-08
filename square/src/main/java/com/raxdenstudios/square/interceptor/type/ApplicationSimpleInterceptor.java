package com.raxdenstudios.square.interceptor.type;

import android.app.Application;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorInteractor;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an application interceptor.
 */
public abstract class ApplicationSimpleInterceptor
        extends ApplicationInterceptor<InterceptorInteractor, InterceptorCallback<InterceptorInteractor>> {

    public ApplicationSimpleInterceptor(Application application) {
        super(application);
    }

}
