package com.raxdenstudios.square.fragment.interceptor.manager;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.InterceptorCallback;
import com.raxdenstudios.square.fragment.InterceptorFragment;

/**
 * Created by agomez on 02/06/2015.
 */
public class InterceptorFragmentImpl<I extends Interceptor>
        implements IInterceptorFragment, InterceptorCallback {

    private static final String TAG = InterceptorFragmentImpl.class.getSimpleName();

    protected Fragment mFragment;
    protected I mCallbacks;

    public InterceptorFragmentImpl(Fragment fragment) {
        if (!(fragment instanceof InterceptorFragment)) {
            throw new IllegalStateException(this.getClass().getSimpleName()+" interceptor must be used just on IInterceptorFragment");
        }
        mFragment = fragment;

        mCallbacks = (I)mFragment;
        mCallbacks.onInterceptorCreated(this);
    }

    @Override
    public void onInterceptorSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onInterceptorAttach(Activity activity) {

    }

    @Override
    public void onInterceptorAttach(Context context) {

    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {

    }

    @Override
    public View onInterceptorCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onInterceptorViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onInterceptorActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onInterceptorStart() {

    }

    @Override
    public void onInterceptorResume() {

    }

    @Override
    public void onInterceptorPause() {

    }

    @Override
    public void onInterceptorStop() {

    }

    @Override
    public void onInterceptorDestroyView() {

    }

    @Override
    public void onInterceptorDestroy() {

    }

    @Override
    public void onInterceptorDetach() {

    }

    @Override
    public void onInterceptorActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onInterceptorConfigurationChanged(Configuration configuration) {

    }

    public Activity getActivity() {
        return mFragment.getActivity();
    }

    public Context getContext() {
        return mFragment.getActivity();
    }


}
