package com.raxdenstudios.square.application.interceptor.callback;

import com.raxdenstudios.square.InterceptorCallback;

import java.util.Locale;

/**
 * Created by Raxden on 24/07/2016.
 */
public interface LocaleInterceptorCallback extends InterceptorCallback {

    Locale getAppLocale();

    void setAppLocale(Locale locale);

}
