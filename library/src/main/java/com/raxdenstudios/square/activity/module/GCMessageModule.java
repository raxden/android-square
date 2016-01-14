package com.raxdenstudios.square.activity.module;

import android.content.Context;
import android.content.Intent;

import com.raxdenstudios.gcm.model.GCMessage;

/**
 * Created by agomez on 11/11/2015.
 */
public interface GCMessageModule {
    void onGCMessageForegroundArrived(Context context, Intent intent, GCMessage message);
    void onGCMessageBackgroundArrived(Context context, Intent intent, GCMessage message);
}
