package com.raxdenstudios.square.interceptor.type;

import android.app.Fragment;

import com.raxdenstudios.square.interceptor.BaseInterceptor;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorConfig;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

public abstract class FragmentSimpleInterceptor
        extends BaseInterceptor<InterceptorConfig, InterceptorCallback<InterceptorConfig>> {

    public FragmentSimpleInterceptor(Fragment fragment) {
        super(fragment);
    }

}
