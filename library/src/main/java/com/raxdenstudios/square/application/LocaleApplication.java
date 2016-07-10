package com.raxdenstudios.square.application;

import com.raxdenstudios.square.application.interceptor.LocaleInterceptor;

/**
 * Created by agomez on 14/08/2015.
 */
public class LocaleApplication extends InterceptorApplication implements LocaleInterceptor {

    private LocaleInterceptorListener mLocaleInterceptor;

    @Override
    public void onLocaleInterceptorLoaded(LocaleInterceptorListener interceptor) {
        mLocaleInterceptor = interceptor;
    }

    public LocaleInterceptorListener getLocaleInterceptor() {
        return mLocaleInterceptor;
    }

}
