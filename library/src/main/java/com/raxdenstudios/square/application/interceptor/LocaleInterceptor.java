package com.raxdenstudios.square.application.interceptor;

import android.content.Context;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.InterceptorCallback;

import java.util.Locale;

/**
 * Created by agomez on 13/07/2015.
 */
public interface LocaleInterceptor extends Interceptor<LocaleInterceptor.LocaleInterceptorCallback> {

    interface LocaleInterceptorCallback extends InterceptorCallback {
        Locale getLocale();

        String getLanguage(Context context);

        void setLanguage(Context context, String language);
    }
}
