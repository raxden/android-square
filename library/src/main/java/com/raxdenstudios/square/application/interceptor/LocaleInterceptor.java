package com.raxdenstudios.square.application.interceptor;

import android.content.Context;

/**
 * Created by agomez on 13/07/2015.
 */
public interface LocaleInterceptor {

    void onLocaleInterceptorLoaded(LocaleInterceptorListener interceptor);

    interface LocaleInterceptorListener {
        String getLanguage(Context context);
        void setLanguage(Context context, String language);
    }
}
