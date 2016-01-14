package com.raxdenstudios.square.fragment.module;

import android.view.View;

/**
 * Created by agomez on 02/06/2015.
 */
public interface AutoInflateViewModule {

    void onModuleLoaded(AutoInflateViewModuleListener module);

    interface AutoInflateViewModuleListener {
        View getView();
    }

}
