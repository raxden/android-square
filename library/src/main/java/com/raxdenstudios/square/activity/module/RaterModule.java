package com.raxdenstudios.square.activity.module;

import android.content.Context;

import com.raxdenstudios.square.activity.module.impl.RaterModuleImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public interface RaterModule {

    void onModuleLoaded(RaterModuleListener module);
    void onRaterModuleClick(RaterModuleImpl.RaterOption optionSelected);

    interface RaterModuleListener {
        void showRaterDialog(Context context);
        boolean isDontShowAgain(Context context);
        void setDontShowAgain(Context context, boolean dontShowAgain);
        long getLaunchCounter(Context context);
        void setLaunchCounter(Context context, long launchCount);
        void setFirstLaunch(Context context, long firstLaunch);
        long getFirstLaunch(Context context);
    }
}
