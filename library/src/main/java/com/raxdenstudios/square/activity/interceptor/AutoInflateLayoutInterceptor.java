package com.raxdenstudios.square.activity.interceptor;

import android.view.View;

import com.raxdenstudios.square.Interceptor;

/**
 * Created by agomez on 22/05/2015.
 */
public interface AutoInflateLayoutInterceptor extends Interceptor {

    interface AutoInflateLayoutInterceptorCallback {
        View getLayout();
    }

}
