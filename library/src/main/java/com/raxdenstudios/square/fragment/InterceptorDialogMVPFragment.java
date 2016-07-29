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

import com.raxdenstudios.mvp.MVPFragmentDialog;
import com.raxdenstudios.mvp.presenter.IPresenter;
import com.raxdenstudios.square.fragment.interceptor.manager.IInterceptorFragment;
import com.raxdenstudios.square.fragment.interceptor.manager.InterceptorFragmentManager;

import java.util.List;

/**
 * Created by agomez on 29/05/2015.
 */
public abstract class InterceptorDialogMVPFragment<TPresenter extends IPresenter> extends MVPFragmentDialog<TPresenter> {

    private static final String TAG = InterceptorDialogMVPFragment.class.getSimpleName();

    /* Interceptor Manager */
    private InterceptorFragmentManager mInterceptorManager;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getInterceptorManager().onSaveInstanceStateInterceptors(outState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getInterceptorManager().onAttachInterceptors(activity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getInterceptorManager().onAttachInterceptors(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInterceptorManager().onCreateInterceptors(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getInterceptorManager().onCreateViewInterceptors(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getInterceptorManager().onViewCreatedInterceptors(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getInterceptorManager().onActivityCreatedInterceptors(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getInterceptorManager().onResumeInterceptors();
    }

    @Override
    public void onStart() {
        super.onStart();
        getInterceptorManager().onStartInterceptors();
    }

    @Override
    public void onStop() {
        super.onStop();
        getInterceptorManager().onStopInterceptors();
    }

    @Override
    public void onPause() {
        super.onPause();
        getInterceptorManager().onPauseInterceptors();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getInterceptorManager().onDestroyViewInterceptors();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getInterceptorManager().onDestroyInterceptors();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getInterceptorManager().onDetachInterceptors();
    }

    /* Callbacks */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getInterceptorManager().onActivityResultInterceptors(requestCode, resultCode, data);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getInterceptorManager().onConfigurationChangedInterceptors(newConfig);
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
