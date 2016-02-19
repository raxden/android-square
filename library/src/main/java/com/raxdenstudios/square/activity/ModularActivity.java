package com.raxdenstudios.square.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.raxdenstudios.square.activity.module.manager.ModuleActivityManager;

/**
 * Created by agomez on 22/05/2015.
 */
public class ModularActivity extends SquareActivity {

    private static final String TAG = ModularActivity.class.getSimpleName();

    /* Module Manager */
    private ModuleActivityManager mModuleManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getModuleManager().onCreateModules(this, savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getModuleManager().onPostCreateModules(this, savedInstanceState);
    }

    @Override /* Called whenever we call ActivityCompat.invalidateOptionsMenu(this); */
    public boolean onPrepareOptionsMenu(Menu menu) {
        getModuleManager().onPrepareOptionsMenuModules(this, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getModuleManager().onResumeModules(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getModuleManager().onStartModules(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getModuleManager().onPauseModules(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getModuleManager().onStopModules(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getModuleManager().onDestroyModules(this);
    }

    /* Callbacks */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getModuleManager().onActivityResultModules(this, requestCode, resultCode, data);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getModuleManager().onConfigurationChangedModules(this, newConfig);
    }


    @Override
    public void onProgressShow(String progressLabel) {
        super.onProgressShow(progressLabel);
        getModuleManager().onProgressShowModules(this, progressLabel);
    }

    @Override
    public void onProgressHide() {
        super.onProgressHide();
        getModuleManager().onProgressHideModules(this);
    }

    @Override
    public void onBackPressed() {
        if (getModuleManager().onBackPressedModules(this)) {
            return;
        } else {
            super.onBackPressed();
        }
    }

    /* Support methods */

    private ModuleActivityManager getModuleManager() {
        if (mModuleManager == null) {
            mModuleManager = new ModuleActivityManager(this);
            initModules(mModuleManager);
        }
        return mModuleManager;
    }

    protected void initModules(ModuleActivityManager moduleManager) {

    }

}
