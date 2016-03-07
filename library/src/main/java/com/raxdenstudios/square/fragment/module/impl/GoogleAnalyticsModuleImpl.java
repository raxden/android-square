package com.raxdenstudios.square.fragment.module.impl;

import android.content.Context;
import android.util.Log;

import com.raxdenstudios.analytics.GoogleAnalyticsHelper;
import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.square.fragment.ModularFragment;
import com.raxdenstudios.square.fragment.module.GoogleAnalyticsModule;
import com.raxdenstudios.square.fragment.module.manager.ModuleFragmentImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public class GoogleAnalyticsModuleImpl extends ModuleFragmentImpl {

    private static final String TAG = GoogleAnalyticsModuleImpl.class.getSimpleName();

    private GoogleAnalyticsHelper.TrackerName mTrackerName;
    private GoogleAnalyticsModule mCallbacks;

    public GoogleAnalyticsModuleImpl(ModularFragment fragment) {
        if (!(fragment instanceof GoogleAnalyticsModule)) {
            throw new IllegalStateException("Fragment must implement GoogleAnalyticsModule.");
        }
        mCallbacks = (GoogleAnalyticsModule)fragment;
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
            Log.d(TAG, "trackScreen: "+screenName);
            GoogleAnalyticsHelper.getInstance().trackScreen(context, mTrackerName, screenName);
        }
    }

}
