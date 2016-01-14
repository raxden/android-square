package com.raxdenstudios.square.fragment.module.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;

/**
 * Created by agomez on 02/06/2015.
 */
public class ModuleFragmentImpl extends ModuleActivityImpl implements ModuleFragment {

    @Override
    public void onModuleAttach(Activity activity) {

    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {

    }

    @Override
    public View onModuleCreateView(Context context, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onModuleViewCreated(Context context, View view, Bundle savedInstanceState) {

    }

    @Override
    public void onModuleActivityCreated(Context context, Bundle savedInstanceState) {

    }

    @Override
    public void onModuleLoadContent(Context context) {

    }

    @Override
    public void onModuleDetach(Context context) {

    }

    @Override
    public void onRefreshContent(Context context) {

    }

    @Override
    public void onProgressShow(Context context, String progressLabel) {

    }

    @Override
    public void onNotice(Context context, int id, String title, String message) {

    }
}
