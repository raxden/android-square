package com.raxdenstudios.square.interceptor.commons.handlearguments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.raxdenstudios.square.interceptor.DialogFragmentInterceptor;

/**
 * Created by agomez on 22/05/2015.
 */
public class HandleArgumentsDialogFragmentInterceptor extends DialogFragmentInterceptor<HandleArgumentsInterceptorCallback> implements HandleArgumentsInterceptor {

    public HandleArgumentsDialogFragmentInterceptor(@NonNull DialogFragment fragment) {
        super(fragment, null);
    }

    public HandleArgumentsDialogFragmentInterceptor(@NonNull DialogFragment fragment, @NonNull HandleArgumentsInterceptorCallback callback) {
        super(fragment, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getCallback().onHandleArguments(savedInstanceState, getDialogFragment().getArguments());
    }

}
