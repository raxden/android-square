package com.raxdenstudios.square.interceptor.callback;

import com.google.android.gms.cast.framework.CastSession;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.CastSessionInterceptorConfig;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public interface CastSessionInterceptorCallback
        extends InterceptorCallback<CastSessionInterceptorConfig> {

    void onCastConnected(CastSession castSession);

    void onCastDisconnected();

}
