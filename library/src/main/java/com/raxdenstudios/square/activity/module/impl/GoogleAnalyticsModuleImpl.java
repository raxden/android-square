package com.raxdenstudios.square.activity.module.impl;

import android.content.Context;
import android.util.Log;

import com.raxdenstudios.analytics.GoogleAnalyticsHelper;
import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.GoogleAnalyticsModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public class GoogleAnalyticsModuleImpl extends ModuleActivityImpl {

    private static final String TAG = GoogleAnalyticsModuleImpl.class.getSimpleName();

    private GoogleAnalyticsHelper.TrackerName mTrackerName;
    private GoogleAnalyticsModule mCallbacks;

    public GoogleAnalyticsModuleImpl(ModularActivity activity) {
        if (!(activity instanceof GoogleAnalyticsModule)) {
            throw new IllegalStateException("Activity must implement GoogleAnalyticsModule.");
        }
        mCallbacks = (GoogleAnalyticsModule)activity;
        mTrackerName = GoogleAnalyticsHelper.TrackerName.APP_TRACKER;
    }

    @Override
    public void onModuleResume(Context context) {
        super.onModuleResume(context);

        String screenName = null;
        if (mCallbacks != null) {
            screenName = mCallbacks.onLoadScreenNameToTrack();
        }
        if (Utils.hasValue(screenName)) {
            Log.d(TAG, "trackScreen: " + screenName);
            GoogleAnalyticsHelper.getInstance().trackScreen(context, mTrackerName, screenName);
        }
    }

}
