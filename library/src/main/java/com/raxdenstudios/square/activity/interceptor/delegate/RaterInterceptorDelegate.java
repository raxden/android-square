package com.raxdenstudios.square.activity.interceptor.delegate;

import com.raxdenstudios.square.InterceptorDelegate;

/**
 * Created by Raxden on 23/07/2016.
 */
public interface RaterInterceptorDelegate extends InterceptorDelegate {

    void showRaterDialog();

    boolean isDontShowAgain();

    void setDontShowAgain(boolean dontShowAgain);

    long getLaunchCounter();

    void setLaunchCounter(long launchCount);

    void setFirstLaunch(long firstLaunch);

    long getFirstLaunch();

}
