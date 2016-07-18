package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.impl.RaterInterceptorImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public interface RaterInterceptor extends Interceptor {

    void onRaterInterceptorClick(RaterInterceptorImpl.RaterOption optionSelected);

    interface RaterInterceptorCallback {
        void showRaterDialog();

        boolean isDontShowAgain();

        void setDontShowAgain(boolean dontShowAgain);

        long getLaunchCounter();

        void setLaunchCounter(long launchCount);

        void setFirstLaunch(long firstLaunch);

        long getFirstLaunch();
    }
}
