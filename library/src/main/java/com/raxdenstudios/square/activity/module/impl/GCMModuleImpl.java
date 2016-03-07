package com.raxdenstudios.square.activity.module.impl;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.gcm.GCMHelper;
import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.GCMModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public class GCMModuleImpl extends ModuleActivityImpl {

    private static final String TAG = GCMModule.class.getSimpleName();

    private GCMModule mCallbacks;

    public GCMModuleImpl(ModularActivity activity) {
        if (!(activity instanceof GCMModule)) {
            throw new IllegalStateException("Activity must implement GCMModule.");
        }
        mCallbacks = (GCMModule)activity;
    }

    @Override
    public void onModuleCreate(Context context, Bundle bundle) {
        super.onModuleCreate(context, bundle);

        registerGCM(context);
    }

    /* Support methods */

    private void registerGCM(Context context) {
        Log.d(TAG, "[registerGCM] Try to register GCM...");
        if (!Utils.checkPlayServices(context)) {
            Log.e(TAG, "[registerGCM][onGooglePlayServicesNotSupported] PlayServices not supported");
            if (mCallbacks != null) mCallbacks.onDeviceNotRegisteredOnGCM("PlayServices not supported");
        }
        if (mCallbacks != null && mCallbacks.onLoadSenderId() != null) {
            GCMHelper.getInstance().registerPlayServices(context, mCallbacks.onLoadSenderId(), new GCMHelper.OnGCMRegisterListener() {
                @Override
                public void onGooglePlayServicesNotSupported() {
                    Log.e(TAG, "[registerGCM][onGooglePlayServicesNotSupported] PlayServices not supported");
                    if (mCallbacks != null) mCallbacks.onDeviceNotRegisteredOnGCM("PlayServices not supported");
                }

                @Override
                public void onDeviceRegistered(String registrationId) {
                    Log.d(TAG, "[registerGCM][onDeviceRegistered] Device registered with this registrationId: "+registrationId);
                    if (mCallbacks != null) mCallbacks.onDeviceRegisteredOnGCM(registrationId);
                }

                @Override
                public void onDeviceNotRegistered(String message) {
                    Log.e(TAG, "[registerGCM][onDeviceNotRegistered] Device not registered");
                    if (mCallbacks != null) mCallbacks.onDeviceNotRegisteredOnGCM(message);
                }
            });
        }
    }
}