package com.raxdenstudios.square.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.activity.module.ToolbarModule;

/**
 * Created by agomez on 02/06/2015.
 */
public class ToolbarActivity extends ModularActivity implements ToolbarModule {

    private ToolbarModuleListener mToolbarModule;

    @Override
    public Toolbar onCreateToolbarView(Bundle savedInstanceState) {
        return (Toolbar)findViewById(R.id.app__toolbar);
    }

    @Override
    public void onModuleLoaded(ToolbarModuleListener module) {
        mToolbarModule = module;
    }

    public ToolbarModuleListener getToolbarModule() {
        return mToolbarModule;
    }

}
