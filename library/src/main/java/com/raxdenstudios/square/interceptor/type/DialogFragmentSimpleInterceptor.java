package com.raxdenstudios.square.interceptor.type;

import android.app.DialogFragment;

import com.raxdenstudios.square.interceptor.BaseInterceptor;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorInteractor;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an DialogFragment interceptor.
 */
public abstract class DialogFragmentSimpleInterceptor
        extends BaseInterceptor<InterceptorInteractor, InterceptorCallback<InterceptorInteractor>> {

    public DialogFragmentSimpleInterceptor(DialogFragment dialogFragment) {
        super(dialogFragment);
    }

}
