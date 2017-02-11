package com.raxdenstudios.square.interceptor.timber;

import android.app.Application;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.ApplicationInterceptor;

import timber.log.Timber;

/**
 * Created by Ángel Gómez on 24/07/2016.
 */
public class TimberApplicationInterceptor
        extends ApplicationInterceptor<TimberInteractor, TimberInterceptorCallback>
        implements TimberInteractor {

    public TimberApplicationInterceptor(@NonNull Application application, @NonNull TimberInterceptorCallback callback) {
        super(application, callback);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.Tree tree = mCallback.onCreateTimberTree();
        if (tree != null) {
            Timber.plant(tree);
        }
    }

}
