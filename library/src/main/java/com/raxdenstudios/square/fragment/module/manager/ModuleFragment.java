package com.raxdenstudios.square.fragment.module.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.activity.module.manager.ModuleActivity;

/**
 * Created by agomez on 02/06/2015.
 */
public interface ModuleFragment extends ModuleActivity {

    void onModuleAttach(Activity activity);
    View onModuleCreateView(Context context, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    void onModuleViewCreated(Context context, View view, Bundle savedInstanceState);
    void onModuleActivityCreated(Context context, Bundle savedInstanceState);
    void onModuleLoadContent(Context context);
    void onModuleDetach(Context context);

    void onRefreshContent(Context context);
    void onProgressShow(Context context, String progressLabel);
    void onNotice(Context context, int id, String title, String message);
}
