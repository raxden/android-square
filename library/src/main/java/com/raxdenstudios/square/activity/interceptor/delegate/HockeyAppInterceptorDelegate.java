package com.raxdenstudios.square.activity.interceptor.delegate;

import com.raxdenstudios.square.InterceptorDelegate;

/**
 * Created by Raxden on 27/07/2016.
 */
public interface HockeyAppInterceptorDelegate extends InterceptorDelegate {

    // Set TRUE if for store builds!
    void setStoreBuild(boolean storeBuild);

}
