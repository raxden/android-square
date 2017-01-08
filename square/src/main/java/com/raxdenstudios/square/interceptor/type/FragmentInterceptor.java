package com.raxdenstudios.square.interceptor.type;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.interceptor.BaseInterceptor;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorInteractor;
import com.raxdenstudios.square.lifecycle.FragmentLifecycle;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an fragment interceptor.
 */
public abstract class FragmentInterceptor<TInteractor extends InterceptorInteractor, TCallback extends InterceptorCallback<TInteractor>>
        extends BaseInterceptor<TInteractor, TCallback>
        implements FragmentLifecycle {

    protected Fragment mFragment;

    public FragmentInterceptor(Fragment fragment) {
        super(fragment);
        mFragment = fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onAttach(Activity activity) {

    }

    @Override
    public void onAttach(Context context) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, View inflatedView, ViewGroup container, Bundle savedInstanceState) {
        return inflatedView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {

    }
}
