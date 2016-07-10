package com.raxdenstudios.square.activity.interceptor;

import android.view.View;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.InterceptorCallback;

/**
 * Created by agomez on 22/05/2015.
 */
public interface AutoInflateLayoutInterceptor extends Interceptor<AutoInflateLayoutInterceptor.AutoInflateLayoutInterceptorCallback>{

    interface AutoInflateLayoutInterceptorCallback extends InterceptorCallback {
        View getLayout();
    }

}
