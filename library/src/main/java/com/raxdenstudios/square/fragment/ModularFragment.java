package com.raxdenstudios.square.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.fragment.module.manager.ModuleFragmentManager;

/**
 * Created by agomez on 29/05/2015.
 */
public abstract class ModularFragment extends SquareFragment {

    private static final String TAG = ModularFragment.class.getSimpleName();

    /* Module Manager */
    private ModuleFragmentManager mModuleManager;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getModuleManager().onAttachModules(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getModuleManager().onCreateModules(getActivity(), savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getModuleManager().onCreateViewModules(getActivity(), inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getModuleManager().onViewCreatedModules(getActivity(), view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getModuleManager().onActivityCreatedModules(getActivity(), savedInstanceState);
    }

    @Override
    public void onLoadContent() {
        super.onLoadContent();
        getModuleManager().onLoadContentModules(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        getModuleManager().onResumeModules(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        getModuleManager().onStartModules(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        getModuleManager().onStopModules(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        getModuleManager().onPauseModules(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getModuleManager().onDestroyViewModules(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getModuleManager().onDestroyModules(getActivity());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getModuleManager().onDetachModules(getActivity());
    }

    /* Callbacks */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getModuleManager().onActivityResultModules(getActivity(), requestCode, resultCode, data);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getModuleManager().onConfigurationChangedModules(getActivity(), newConfig);
    }

    @Override
    public void onRefreshContent() {
        super.onRefreshContent();
        getModuleManager().onRefreshContentModules(getActivity());
    }

    @Override
    public void onProgressShow(String progressLabel) {
        super.onProgressShow(progressLabel);
        getModuleManager().onProgressShowModules(getActivity(),progressLabel);
    }

    @Override
    public void onNotice(int id, String title, String message) {
        super.onNotice(id, title, message);
        getModuleManager().onNoticeModules(getActivity(),id,title,message);
    }

    /* Support methods */

    private ModuleFragmentManager getModuleManager() {
        if (mModuleManager == null) {
            mModuleManager = new ModuleFragmentManager(this);
            initModules(mModuleManager);
        }
        return mModuleManager;
    }

    protected void initModules(ModuleFragmentManager moduleManager) {

    }

}
