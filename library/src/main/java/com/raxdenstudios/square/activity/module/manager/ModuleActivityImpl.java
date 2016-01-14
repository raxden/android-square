package com.raxdenstudios.square.activity.module.manager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.raxdenstudios.square.activity.ModularActivity;

/**
 * Created by agomez on 21/04/2015.
 */
public class ModuleActivityImpl implements ModuleActivity {

    private static final String TAG = ModuleActivityImpl.class.getSimpleName();

    @Override
    public void onModuleActivityResult(Context context, int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onModuleDestroyView(Context context) {

    }

    @Override
    public void onModuleConfigurationChanged(Context context, Configuration configuration) {

    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        checkContextIfModularActivityInstance(context);
    }

    @Override
    public void onModulePostCreate(Context context, Bundle savedInstanceState) {

    }

    @Override
    public void onModulePrepareOptionsMenu(Context context, Menu menu) {

    }

    @Override
    public void onModuleResume(Context context) {

    }

    @Override
    public void onModuleStart(Context context) {

    }

    @Override
    public void onModuleStop(Context context) {

    }

    @Override
    public void onModulePause(Context context) {

    }

    @Override
    public boolean onModuleBackPressed(Context context) {
        return false;
    }

    @Override
    public void onModuleDestroy(Context context) {

    }

    @Override
    public void onProgressShow(Context context, String progressLabel) {

    }

    @Override
    public void onProgressHide(Context context) {

    }

    protected void checkContextIfModularActivityInstance(Context context) {
        if (!(context instanceof ModularActivity)) {
            throw new IllegalStateException(this.getClass().getSimpleName()+" module must be used just on ModularActivity");
        }
    }
}
