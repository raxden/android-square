package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by agomez on 25/05/2015.
 */
public interface InflateLayoutInterceptor {

    View onCreateLayout(LayoutInflater inflater, ViewGroup view, Bundle savedInstanceState);
    void onInterceptorLoaded(InflateLayoutInterceptorListener interceptor);

    interface InflateLayoutInterceptorListener {
        View getLayout();
    }

}
