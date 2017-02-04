package com.raxdenstudios.square.interceptor.bundlearguments;

import android.app.Fragment;
import android.os.Bundle;

import com.raxdenstudios.square.interceptor.FragmentInterceptor;

/**
 * Created by agomez on 22/05/2015.
 */
public class BundleArgumentsFragmentInterceptor
        extends FragmentInterceptor<BundleArgumentsInteractor, BundleArgumentsInterceptorCallback>
        implements BundleArgumentsInteractor {

    public BundleArgumentsFragmentInterceptor(Fragment fragment, BundleArgumentsInterceptorCallback callback) {
        super(fragment, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCallback.onHandleArguments(savedInstanceState, mFragment.getArguments());
    }

}
