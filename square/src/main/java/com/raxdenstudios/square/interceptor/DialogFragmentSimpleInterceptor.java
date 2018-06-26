package com.raxdenstudios.square.interceptor;

import android.app.DialogFragment;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an DialogFragment interceptor.
 */
public abstract class DialogFragmentSimpleInterceptor extends DialogFragmentInterceptor<InterceptorCallback> {

    public DialogFragmentSimpleInterceptor(DialogFragment dialogFragment) {
        super(dialogFragment);
    }

}
