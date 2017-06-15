package com.raxdenstudios.square.interceptor.cast;

import com.google.android.gms.cast.framework.CastSession;
import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public interface CastSessionInterceptorCallback extends InterceptorCallback {

    void onCastConnected(CastSession castSession);

    void onCastDisconnected();

}
