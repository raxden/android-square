package com.raxdenstudios.square.interceptor;

import android.app.DialogFragment;

import com.raxdenstudios.square.interceptor.BaseInterceptor;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.Interactor;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an DialogFragment interceptor.
 */
public abstract class DialogFragmentSimpleInterceptor
        extends BaseInterceptor<Interactor, InterceptorCallback<Interactor>> {

    public DialogFragmentSimpleInterceptor(DialogFragment dialogFragment) {
        super(dialogFragment, null);
    }

}
