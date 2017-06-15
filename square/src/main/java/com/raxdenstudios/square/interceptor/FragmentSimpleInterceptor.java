package com.raxdenstudios.square.interceptor;

import android.app.Fragment;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an fragment interceptor.
 */
public abstract class FragmentSimpleInterceptor extends BaseInterceptor<InterceptorCallback> {

    public FragmentSimpleInterceptor(Fragment fragment) {
        super(fragment);
    }

}
