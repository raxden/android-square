package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.HockeyAppInterceptorDelegate;

/**
 * Created by Raxden on 27/07/2016.
 */
public interface HockeyAppInterceptor extends Interceptor {

    void onInterceptorCreated(HockeyAppInterceptorDelegate delegate);

}
