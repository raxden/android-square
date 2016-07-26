package com.raxdenstudios.square.activity.interceptor.callback;

import com.raxdenstudios.square.InterceptorCallback;

/**
 * Created by Raxden on 23/07/2016.
 */
public interface RaterInterceptorCallback extends InterceptorCallback {

    void showRaterDialog();

    boolean isDontShowAgain();

    void setDontShowAgain(boolean dontShowAgain);

    long getLaunchCounter();

    void setLaunchCounter(long launchCount);

    void setFirstLaunch(long firstLaunch);

    long getFirstLaunch();

}
