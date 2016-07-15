package com.raxdenstudios.square.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.mvp.MVPFragment;
import com.raxdenstudios.mvp.presenter.IPresenter;
import com.raxdenstudios.square.fragment.interceptor.manager.IInterceptorFragment;
import com.raxdenstudios.square.fragment.interceptor.manager.InterceptorFragmentManager;

import java.util.List;

/**
 * Created by agomez on 29/05/2015.
 */
public abstract class InterceptorFragment<TPresenter extends IPresenter> extends MVPFragment<TPresenter> {

    private static final String TAG = InterceptorFragment.class.getSimpleName();

    /* Interceptor Manager */
    private InterceptorFragmentManager mInterceptorManager;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getInterceptorManager().onAttachInterceptors(activity);
    }

    @Override
    public void onAttach(Context context) {
        getInterceptorManager().onAttachInterceptors(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInterceptorManager().onCreateInterceptors(getActivity(), savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getInterceptorManager().onCreateViewInterceptors(getActivity(), inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getInterceptorManager().onViewCreatedInterceptors(getActivity(), view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getInterceptorManager().onActivityCreatedInterceptors(getActivity(), savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getInterceptorManager().onResumeInterceptors(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        getInterceptorManager().onStartInterceptors(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        getInterceptorManager().onStopInterceptors(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        getInterceptorManager().onPauseInterceptors(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getInterceptorManager().onDestroyViewInterceptors(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getInterceptorManager().onDestroyInterceptors(getActivity());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getInterceptorManager().onDetachInterceptors(getActivity());
    }

    /* Callbacks */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getInterceptorManager().onActivityResultInterceptors(getActivity(), requestCode, resultCode, data);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getInterceptorManager().onConfigurationChangedInterceptors(getActivity(), newConfig);
    }

    /* Support methods */

    private InterceptorFragmentManager getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = new InterceptorFragmentManager(this);
            addInterceptor(mInterceptorManager.getInterceptors());
        }
        return mInterceptorManager;
    }

    protected void addInterceptor(List<IInterceptorFragment> interceptors) {

    }

}
