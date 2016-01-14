package com.raxdenstudios.square.activity.module.manager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

/**
 * Created by Raxden on 22/03/2015.
 */
public interface ModuleActivity {

    void onModuleCreate(Context context, Bundle savedInstanceState);
    void onModulePostCreate(Context context, Bundle savedInstanceState);
    void onModulePrepareOptionsMenu(Context context, Menu menu);
    void onModuleStart(Context context);
    void onModuleResume(Context context);
    void onModulePause(Context context);
    void onModuleStop(Context context);
    void onModuleDestroy(Context context);
    void onModuleDestroyView(Context context);

    boolean onModuleBackPressed(Context context);
    void onModuleActivityResult(Context context, int requestCode, int resultCode, Intent data);
    void onModuleConfigurationChanged(Context context, Configuration configuration);
    void onProgressShow(Context context, String progressLabel);
    void onProgressHide(Context context);

}
