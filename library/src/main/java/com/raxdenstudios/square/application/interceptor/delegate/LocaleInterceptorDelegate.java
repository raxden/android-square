package com.raxdenstudios.square.application.interceptor.delegate;

import com.raxdenstudios.square.InterceptorDelegate;

import java.util.Locale;

/**
 * Created by Raxden on 24/07/2016.
 */
public interface LocaleInterceptorDelegate extends InterceptorDelegate {

    Locale getAppLocale();

    void setAppLocale(Locale locale);

}
