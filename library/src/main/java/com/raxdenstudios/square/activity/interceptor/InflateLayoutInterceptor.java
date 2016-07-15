package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.Interceptor;

/**
 * Created by agomez on 25/05/2015.
 */
public interface InflateLayoutInterceptor extends Interceptor {

    View onCreateLayout(LayoutInflater inflater, ViewGroup view, Bundle savedInstanceState);

    interface InflateLayoutInterceptorCallback {
        View getLayout();
    }

}
