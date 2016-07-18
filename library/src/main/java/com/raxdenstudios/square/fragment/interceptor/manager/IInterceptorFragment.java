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

    void onInterceptorSaveInstanceState(Bundle outState);

    void onInterceptorAttach(Activity activity);

    void onInterceptorAttach(Context context);

    void onInterceptorCreate(Bundle savedInstanceState);

    View onInterceptorCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    void onInterceptorViewCreated(View view, Bundle savedInstanceState);

    void onInterceptorActivityCreated(Bundle savedInstanceState);

    void onInterceptorStart();

    void onInterceptorResume();

    void onInterceptorPause();

    void onInterceptorStop();

    void onInterceptorDestroy();

    void onInterceptorDestroyView();

    void onInterceptorDetach();

    void onInterceptorActivityResult(int requestCode, int resultCode, Intent data);

    void onInterceptorConfigurationChanged(Configuration configuration);
}
