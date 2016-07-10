package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;

/**
 * Created by agomez on 22/05/2015.
 */
public interface BundleExtrasInterceptor {
    void onHandleExtras(Bundle savedInstanceState, Bundle intentExtras);
}
