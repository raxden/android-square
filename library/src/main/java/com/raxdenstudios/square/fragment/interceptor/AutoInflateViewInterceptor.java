package com.raxdenstudios.square.fragment.interceptor;

import android.view.View;

/**
 * Created by agomez on 02/06/2015.
 */
public interface AutoInflateViewInterceptor {

    void onInterceptorLoaded(AutoInflateViewInterceptorCallback callback);

    interface AutoInflateViewInterceptorCallback {
        View getView();
    }

}
