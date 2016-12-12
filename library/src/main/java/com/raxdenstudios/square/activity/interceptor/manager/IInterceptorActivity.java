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

    void onInterceptorSaveInstanceState(Bundle outState);

    void onInterceptorAttachBaseContextInterceptors(Context newBase);

    void onInterceptorCreate(Bundle savedInstanceState);

    void onInterceptorPostCreate(Bundle savedInstanceState);

    void onInterceptorCreateOptionsMenu(Menu menu);

    void onInterceptorPrepareOptionsMenu(Menu menu);

    void onInterceptorStart();

    void onInterceptorResume();

    void onInterceptorPause();

    void onInterceptorStop();

    void onInterceptorDestroy();

    boolean onInterceptorBackPressed();

    void onInterceptorActivityResult(int requestCode, int resultCode, Intent data);

    void onInterceptorConfigurationChanged(Configuration configuration);

}
