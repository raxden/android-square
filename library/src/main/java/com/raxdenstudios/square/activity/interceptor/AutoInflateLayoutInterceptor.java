package com.raxdenstudios.square.activity.interceptor;

import android.view.View;

/**
 * Created by agomez on 22/05/2015.
 */
public interface AutoInflateLayoutInterceptor {

    void onInterceptorLoaded(AutoInflateLayoutInterceptorCallback callback);

    interface AutoInflateLayoutInterceptorCallback {
        View getLayout();
    }

}
