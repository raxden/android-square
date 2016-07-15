package com.raxdenstudios.square.fragment.interceptor;

import android.view.View;

import com.raxdenstudios.square.Interceptor;

/**
 * Created by agomez on 02/06/2015.
 */
public interface AutoInflateViewInterceptor extends Interceptor {

    interface AutoInflateViewInterceptorCallback {
        View getView();
    }

}
