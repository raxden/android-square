package com.raxdenstudios.square.sample;

import com.raxdenstudios.square.SquareApplication;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.fabric.FabricApplicationInterceptorImpl;
import com.raxdenstudios.square.interceptor.fabric.FabricInterceptor;
import com.raxdenstudios.square.interceptor.fabric.FabricInterceptorCallback;
import com.raxdenstudios.square.interceptor.timber.TimberApplicationInterceptorImpl;
import com.raxdenstudios.square.interceptor.timber.TimberInterceptor;
import com.raxdenstudios.square.interceptor.timber.TimberInterceptorCallback;

import java.util.List;

import timber.log.Timber;

/**
 * Created by Ángel Gómez on 12/06/2017.
 */

public class SampleApplication extends SquareApplication implements FabricInterceptorCallback, TimberInterceptorCallback {

    @Override
    public Timber.Tree onCreateTimberTree() {
        return null;
    }

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        FabricInterceptor fabricInterceptor = new FabricApplicationInterceptorImpl(this, this);
        TimberInterceptor timberInterceptor = new TimberApplicationInterceptorImpl(this, this);

        interceptorList.add(fabricInterceptor);
        interceptorList.add(timberInterceptor);
    }

}
