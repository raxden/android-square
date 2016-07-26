package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.AutoInflateLayoutInterceptorDelegate;

/**
 * Created by agomez on 22/05/2015.
 */
public interface AutoInflateLayoutInterceptor extends Interceptor {

    void onInterceptorCreated(AutoInflateLayoutInterceptorDelegate callback);

    void onViewCreated(View view, Bundle savedInstanceState);

}
