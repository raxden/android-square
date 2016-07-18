package com.raxdenstudios.square.application.interceptor.impl;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.square.application.interceptor.LocaleInterceptor;
import com.raxdenstudios.square.application.interceptor.manager.InterceptorApplicationImpl;

import java.util.Locale;

/**
 * Created by agomez on 13/07/2015.
 */
public class LocaleInterceptorImpl extends InterceptorApplicationImpl implements LocaleInterceptor.LocaleInterceptorCallback {

    public static final String APP_LANGUAGE = "app_language";

    private LocaleInterceptor mCallbacks;
    private Locale appLocale;

    public LocaleInterceptorImpl(Application application) {
        super(application);
        if (!(application instanceof LocaleInterceptor)) {
            throw new IllegalStateException("Application must implement LocaleInterceptor.");
        }
        mCallbacks = (LocaleInterceptor)application;
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initLocalization();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initLocalization();
    }

    @Override
    public Locale getLocale() {
        return appLocale;
    }

    @Override
    public String getLanguage() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        String language = settings.getString(APP_LANGUAGE, "");
        if (appLocale == null) {
            appLocale = initLocale(language);
        }
        return language;
    }

    @Override
    public void setLanguage(String language) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(APP_LANGUAGE, language);
        editor.commit();
        appLocale = initLocale(language);
    }

    private void initLocalization() {
        String appLanguage = getLanguage();
        if (Utils.hasValue(appLanguage)) {
            Locale appLocale = initLocale(appLanguage);
            Locale.setDefault(appLocale);
            Configuration config = getContext().getResources().getConfiguration() != null ? getContext().getResources().getConfiguration() : new Configuration();
            config.locale = appLocale;
            getContext().getResources().updateConfiguration(config, getContext().getResources().getDisplayMetrics());
        } else {
            setLanguage(Locale.getDefault().toString());
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
