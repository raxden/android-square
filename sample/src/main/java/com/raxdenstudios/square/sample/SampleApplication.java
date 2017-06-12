package com.raxdenstudios.square.sample;

import com.raxdenstudios.square.SquareApplication;
import com.raxdenstudios.square.interceptor.ApplicationInterceptor;
import com.raxdenstudios.square.interceptor.fabric.FabricApplicationInterceptor;
import com.raxdenstudios.square.interceptor.fabric.FabricInteractor;
import com.raxdenstudios.square.interceptor.fabric.FabricInterceptorCallback;
import com.raxdenstudios.square.interceptor.timber.TimberApplicationInterceptor;
import com.raxdenstudios.square.interceptor.timber.TimberInteractor;
import com.raxdenstudios.square.interceptor.timber.TimberInterceptorCallback;

import java.util.List;

/**
 * Created by Ángel Gómez on 12/06/2017.
 */

public class SampleApplication extends SquareApplication implements
        FabricInterceptorCallback, TimberInterceptorCallback {


    @Override
    public void onInterceptorAttached(FabricInteractor interactor) {

    }

    @Override
    protected void addInterceptor(List<ApplicationInterceptor> interceptors) {

        FabricInteractor fabricInterceptor = new FabricApplicationInterceptor(this);
        TimberInteractor timberInterceptor = new TimberApplicationInterceptor(this);

        fabricInterceptor.setOnInterceptorCallback(this);
        timberInterceptor.setOnInterceptorCallback(this);

        interceptors.add(fabricInterceptor);
        interceptors.add(timberInterceptor);
    }

}
