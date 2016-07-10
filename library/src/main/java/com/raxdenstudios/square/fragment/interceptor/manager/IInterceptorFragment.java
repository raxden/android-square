package com.raxdenstudios.square.fragment.interceptor.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by agomez on 02/06/2015.
 */
public interface IInterceptorFragment {

    void onInterceptorAttach(Activity activity);
    void onInterceptorAttach(Context context);
    void onInterceptorCreate(Context context, Bundle savedInstanceState);
    View onInterceptorCreateView(Context context, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    void onInterceptorViewCreated(Context context, View view, Bundle savedInstanceState);
    void onInterceptorActivityCreated(Context context, Bundle savedInstanceState);
    void onInterceptorStart(Context context);
    void onInterceptorResume(Context context);
    void onInterceptorPause(Context context);
    void onInterceptorStop(Context context);
    void onInterceptorDestroy(Context context);
    void onInterceptorDestroyView(Context context);
    void onInterceptorDetach(Context context);

    void onInterceptorActivityResult(Context context, int requestCode, int resultCode, Intent data);
    void onInterceptorConfigurationChanged(Context context, Configuration configuration);
}
