package com.raxdenstudios.square.interceptor.commons.handlearguments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.FragmentInterceptor;

/**
 * Created by agomez on 22/05/2015.
 */
public class HandleArgumentsFragmentInterceptor extends FragmentInterceptor<HandleArgumentsInterceptorCallback> implements HandleArgumentsInterceptor {

    public HandleArgumentsFragmentInterceptor(@NonNull Fragment fragment) {
        super(fragment);
    }

    public HandleArgumentsFragmentInterceptor(@NonNull Fragment fragment, @NonNull HandleArgumentsInterceptorCallback callback) {
        super(fragment, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCallback.onHandleArguments(savedInstanceState, mFragment.getArguments());
    }

}
