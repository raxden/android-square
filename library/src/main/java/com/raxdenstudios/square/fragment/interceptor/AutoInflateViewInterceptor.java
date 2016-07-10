package com.raxdenstudios.square.fragment.interceptor;

import android.view.View;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.InterceptorCallback;

/**
 * Created by agomez on 02/06/2015.
 */
public interface AutoInflateViewInterceptor extends Interceptor<AutoInflateViewInterceptor.AutoInflateViewInterceptorCallback> {

    interface AutoInflateViewInterceptorCallback extends InterceptorCallback {
        View getView();
    }

}
