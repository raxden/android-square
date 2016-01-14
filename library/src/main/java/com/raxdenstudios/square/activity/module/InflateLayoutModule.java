package com.raxdenstudios.square.activity.module;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by agomez on 25/05/2015.
 */
public interface InflateLayoutModule {

    View onCreateLayout(LayoutInflater inflater, ViewGroup view, Bundle savedInstanceState);
    void onModuleLoaded(InflateLayoutModuleListener module);

    interface InflateLayoutModuleListener {
        View getLayout();
    }

}
