package com.raxdenstudios.square.activity.module.impl;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.GCMModule;
import com.raxdenstudios.square.activity.module.GCMessageModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;
import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.gcm.GCMHelper;
import com.raxdenstudios.gcm.model.GCMessage;

/**
 * Created by agomez on 06/05/2015.
 */
public class GCMessageModuleImpl extends ModuleActivityImpl {

    private static final String TAG = GCMessageModuleImpl.class.getSimpleName();

    private GCMessageModule mCallbacks;

    protected BroadcastReceiver mPushReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey(GCMessage.class.getSimpleName())) {
                GCMessage message = intent.getExtras().getParcelable(GCMessage.class.getSimpleName());
                if (message != null) {
                    if (GCMHelper.getInstance().consumeGCMMessage(context, message.getId()) != null) {
                        if (mCallbacks != null) mCallbacks.onGCMessageForegroundArrived(context, intent, message);
                    }
                }
            }
        }
    };

    public GCMessageModuleImpl(ModularActivity activity) {
        if (!(activity instanceof GCMModule)) {
            throw new IllegalStateException("Activity must implement GCMessageModule.");
        }
        mCallbacks = (GCMessageModule)activity;
    }

    @Override
    public void onModuleCreate(Context context, Bundle bundle) {
        super.onModuleCreate(context, bundle);

        Bundle extras = ((Activity) context).getIntent().getExtras();
        if (extras != null && extras.containsKey(GCMessage.class.getSimpleName())) {
            GCMessage message = extras.getParcelable(GCMessage.class.getSimpleName());
            if (message != null) {
                if (mCallbacks != null) mCallbacks.onGCMessageBackgroundArrived(context, ((Activity) context).getIntent(), message);
            }
        }
    }

    @Override
    public void onModuleResume(Context context) {
        super.onModuleResume(context);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Utils.getPackageName(context)+"."+GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE);
        intentFilter.setPriority(2);

        if (mPushReceiver != null) context.registerReceiver(mPushReceiver, intentFilter);
    }

    @Override
    public void onModulePause(Context context) {
        super.onModulePause(context);

        if (mPushReceiver != null) context.unregisterReceiver(mPushReceiver);
    }

}