package com.raxdenstudios.square.interceptor;

import android.app.Fragment;

import com.raxdenstudios.square.interceptor.BaseInterceptor;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.Interactor;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an fragment interceptor.
 */
public abstract class FragmentSimpleInterceptor
        extends BaseInterceptor<Interactor, InterceptorCallback<Interactor>> {

    public FragmentSimpleInterceptor(Fragment fragment) {
        super(fragment, null);
    }

}
