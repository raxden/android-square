package com.raxdenstudios.square.application.module;

import android.content.Context;

/**
 * Created by agomez on 13/07/2015.
 */
public interface LocaleModule {

    void onLocaleModuleLoaded(LocaleModuleListener module);

    interface LocaleModuleListener {
        String getLanguage(Context context);
        void setLanguage(Context context, String language);
    }
}
