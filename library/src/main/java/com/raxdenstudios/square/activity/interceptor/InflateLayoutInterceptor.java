package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.InflateLayoutInterceptorDelegate;

/**
 * Created by agomez on 25/05/2015.
 */
public interface InflateLayoutInterceptor extends Interceptor {

    void onInterceptorCreated(InflateLayoutInterceptorDelegate callback);

    View onCreateView(LayoutInflater inflater, Bundle savedInstanceState);

    void onViewCreated(View view, Bundle savedInstanceState);

}
