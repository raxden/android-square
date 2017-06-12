package com.raxdenstudios.square.interceptor.butterknife;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.interceptor.FragmentInterceptor;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Raxden on 23/07/2016.
 */
public class ButterKnifeFragmentInterceptor
        extends FragmentInterceptor<ButterKnifeInterceptorInteractor, ButterKnifeInterceptorCallback>
        implements ButterKnifeInterceptorInteractor {

    private Unbinder unbinder;

    public ButterKnifeFragmentInterceptor(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ButterKnifeFragmentInterceptor(@NonNull Fragment fragment, @NonNull ButterKnifeInterceptorCallback callback) {
        super(fragment, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, View inflatedView, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(mFragment, inflatedView);
        return inflatedView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
