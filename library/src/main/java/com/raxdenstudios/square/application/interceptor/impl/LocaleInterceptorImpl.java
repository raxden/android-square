package com.raxdenstudios.square.application.interceptor.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.square.application.InterceptorApplication;
import com.raxdenstudios.square.application.InterceptorMultiDexApplication;
import com.raxdenstudios.square.application.interceptor.LocaleInterceptor;
import com.raxdenstudios.square.application.interceptor.manager.InterceptorApplicationImpl;

import java.util.Locale;

/**
 * Created by agomez on 13/07/2015.
 */
public class LocaleInterceptorImpl extends InterceptorApplicationImpl implements LocaleInterceptor.LocaleInterceptorListener {

    public static final String APP_LANGUAGE = "app_language";

    private LocaleInterceptor mCallbacks;
    private Locale appLocale;

    public LocaleInterceptorImpl(InterceptorApplication application) {
        if (!(application instanceof LocaleInterceptor)) {
            throw new IllegalStateException("Application must implement LocaleInterceptor.");
        }
        mCallbacks = (LocaleInterceptor)application;
    }

    public LocaleInterceptorImpl(InterceptorMultiDexApplication application) {
        if (!(application instanceof LocaleInterceptor)) {
            throw new IllegalStateException("Application must implement LocaleInterceptor.");
        }
        mCallbacks = (LocaleInterceptor)application;
    }

    @Override
    public void onConfigurationChanged(Context context, Configuration newConfig) {
        super.onConfigurationChanged(context, newConfig);
        initLocalization(context);
    }

    @Override
    public void onCreate(Context context) {
        super.onCreate(context);
        initLocalization(context);

        if (mCallbacks != null) mCallbacks.onLocaleInterceptorLoaded(this);
    }

    @Override
    public String getLanguage(Context context) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String language = settings.getString(APP_LANGUAGE, "");
        if (appLocale == null) {
            appLocale = initLocale(language);
        }
        return language;
    }

    @Override
    public void setLanguage(Context context, String language) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(APP_LANGUAGE, language);
        editor.commit();
        appLocale = initLocale(language);
    }

    private void initLocalization(Context context) {
        String appLanguage = getLanguage(context);
        if (Utils.hasValue(appLanguage)) {
            Locale appLocale = initLocale(appLanguage);
            Locale.setDefault(appLocale);
            Configuration config = context.getResources().getConfiguration() != null ? context.getResources().getConfiguration() : new Configuration();
            config.locale = appLocale;
            context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        } else {
            setLanguage(context, Locale.getDefault().toString());
        }
    }

    private Locale initLocale(String language) {
        Locale locale = null;
        if (language != null && language.contains("_") && language.split("_").length > 0) {
            locale = new Locale(language.split("_")[0], language.split("_")[1]);
        } else {
            locale = new Locale(language);
        }
        return locale;
    }
}
