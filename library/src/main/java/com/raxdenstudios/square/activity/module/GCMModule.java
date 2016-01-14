package com.raxdenstudios.square.activity.module;

/**
 * Created by agomez on 06/05/2015.
 */
public interface GCMModule {
    String onLoadSenderId();
    void onDeviceRegisteredOnGCM(String registrationId);
    void onDeviceNotRegisteredOnGCM(String message);
}
