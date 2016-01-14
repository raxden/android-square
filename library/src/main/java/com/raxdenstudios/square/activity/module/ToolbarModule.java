package com.raxdenstudios.square.activity.module;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * Created by agomez on 21/05/2015.
 */
public interface ToolbarModule {

    Toolbar onCreateToolbarView(Bundle savedInstanceState);
    void onModuleLoaded(ToolbarModuleListener module);

    interface ToolbarModuleListener {
        Toolbar getToolbar();
    }

}
