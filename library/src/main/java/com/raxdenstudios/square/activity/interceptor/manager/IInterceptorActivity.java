package com.raxdenstudios.square.activity.interceptor.manager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

/**
 * Created by Raxden on 22/03/2015.
 */
public interface IInterceptorActivity {

    void onInterceptorCreate(Context context, Bundle savedInstanceState);
    void onInterceptorPostCreate(Context context, Bundle savedInstanceState);
    void onInterceptorPrepareOptionsMenu(Context context, Menu menu);
    void onInterceptorStart(Context context);
    void onInterceptorResume(Context context);
    void onInterceptorPause(Context context);
    void onInterceptorStop(Context context);
    void onInterceptorDestroy(Context context);

    boolean onInterceptorBackPressed(Context context);
    void onInterceptorActivityResult(Context context, int requestCode, int resultCode, Intent data);
    void onInterceptorConfigurationChanged(Context context, Configuration configuration);

}
