package com.raxdenstudios.square.interceptor.type;

import android.app.Fragment;

import com.raxdenstudios.square.interceptor.BaseInterceptor;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorInteractor;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an fragment interceptor.
 */
public abstract class FragmentSimpleInterceptor
        extends BaseInterceptor<InterceptorInteractor, InterceptorCallback<InterceptorInteractor>> {

    public FragmentSimpleInterceptor(Fragment fragment) {
        super(fragment);
    }

}
