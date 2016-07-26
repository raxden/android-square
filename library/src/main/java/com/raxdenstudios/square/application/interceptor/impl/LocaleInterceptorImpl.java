package com.raxdenstudios.square.application.interceptor.impl;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.square.application.interceptor.LocaleInterceptor;
import com.raxdenstudios.square.application.interceptor.delegate.LocaleInterceptorDelegate;
import com.raxdenstudios.square.application.interceptor.manager.InterceptorApplicationImpl;

import java.util.Locale;

/**
 * Created by agomez on 13/07/2015.
 */
public class LocaleInterceptorImpl extends InterceptorApplicationImpl implements LocaleInterceptorDelegate {

    private static final String TAG = LocaleInterceptorImpl.class.getSimpleName();

    public static final String APP_LANGUAGE = "app_language";

    private LocaleInterceptor mCallbacks;
    private Locale appLocale;

    public LocaleInterceptorImpl(Application application) {
        super(application);
        mCallbacks.onInterceptorCreated(this);
        mCallbacks = (LocaleInterceptor)application;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initLocale();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initLocale();
    }

    @Override
    public Locale getAppLocale() {
        if (appLocale == null) {
            String language = getLanguage();
            if (Utils.hasValue(language)) {
                appLocale = new Locale(language);
            } else {
                appLocale = Locale.getDefault();
                setLanguage(appLocale.getLanguage());
            }
        }
        return appLocale;
    }

    @Override
    public void setAppLocale(Locale locale) {
        appLocale = locale;
        setLanguage(appLocale.getLanguage());
    }

    private String getLanguage() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        return settings.getString(APP_LANGUAGE, "");
    }

    private void setLanguage(String language) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(APP_LANGUAGE, language);
        editor.commit();
    }

    private void initLocale() {
        Locale appLocale = getAppLocale();
        Locale.setDefault(appLocale);
        Configuration config = getConfiguration();
        config.locale = appLocale;
        setConfiguration(config);
    }

    private DisplayMetrics getDisplayMetrics() {
        Resources resources = getContext().getResources();
        return resources.getDisplayMetrics();
    }

    private Configuration getConfiguration() {
        Resources resources = getContext().getResources();
        Configuration configuration = resources.getConfiguration();
        if (configuration == null) {
            configuration = new Configuration();
        }
        return configuration;
    }

    private void setConfiguration(Configuration configuration) {
        if (configuration != null) {
            Resources resources = getContext().getResources();
            resources.updateConfiguration(configuration, getDisplayMetrics());
        }
    }

}
