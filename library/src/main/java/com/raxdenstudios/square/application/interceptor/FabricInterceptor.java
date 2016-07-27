package com.raxdenstudios.square.application.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.application.interceptor.delegate.FabricInterceptorDelegate;

/**
 * Created by Raxden on 27/07/2016.
 */
public interface FabricInterceptor extends Interceptor {

    void onInterceptorCreated(FabricInterceptorDelegate delegate);

}
