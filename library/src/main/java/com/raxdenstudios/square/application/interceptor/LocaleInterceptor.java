package com.raxdenstudios.square.application.interceptor;

import android.content.Context;

import java.util.Locale;

/**
 * Created by agomez on 13/07/2015.
 */
public interface LocaleInterceptor {

    void onInterceptorLoaded(LocaleInterceptorCallback callback);

    interface LocaleInterceptorCallback {
        Locale getLocale();

        String getLanguage(Context context);

        void setLanguage(Context context, String language);
    }
}
