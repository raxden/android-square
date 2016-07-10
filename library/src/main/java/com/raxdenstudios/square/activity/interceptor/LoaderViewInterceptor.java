package com.raxdenstudios.square.activity.interceptor;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by agomez on 02/06/2015.
 */
public interface LoaderViewInterceptor {

    void onInterceptorLoaded(LoaderViewInterceptorCallback callback);

    View onCreateLoaderView(Bundle savedInstanceState);

    interface LoaderViewInterceptorCallback {
        void show(Context context);

        void show(Context context, String loaderLabel);

        void show(Context context, int loaderLabelId, String loaderLabel);

        void hide(Context context);
    }
}
