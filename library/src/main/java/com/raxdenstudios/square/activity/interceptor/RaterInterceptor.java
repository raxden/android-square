package com.raxdenstudios.square.activity.interceptor;

import android.content.Context;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.InterceptorCallback;
import com.raxdenstudios.square.activity.interceptor.impl.RaterInterceptorImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public interface RaterInterceptor extends Interceptor<RaterInterceptor.RaterInterceptorCallback> {

    void onRaterInterceptorClick(RaterInterceptorImpl.RaterOption optionSelected);

    interface RaterInterceptorCallback extends InterceptorCallback {
        void showRaterDialog(Context context);
        boolean isDontShowAgain(Context context);
        void setDontShowAgain(Context context, boolean dontShowAgain);
        long getLaunchCounter(Context context);
        void setLaunchCounter(Context context, long launchCount);
        void setFirstLaunch(Context context, long firstLaunch);
        long getFirstLaunch(Context context);
    }
}
