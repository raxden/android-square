package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.IcepickInterceptorDelegate;

/**
 * Created by Raxden on 26/07/2016.
 */
public interface IcepickInterceptor extends Interceptor {

    void onInterceptorCreated(IcepickInterceptorDelegate delegate);

}
