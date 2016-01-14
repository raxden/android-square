package com.raxdenstudios.square.activity.module;

import android.view.View;

/**
 * Created by agomez on 22/05/2015.
 */
public interface AutoInflateLayoutModule {

    void onModuleLoaded(AutoInflateLayoutModuleListener module);

    interface AutoInflateLayoutModuleListener {
        View getLayout();
    }

}
