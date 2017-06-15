package com.raxdenstudios.square.interceptor.rater;

import com.raxdenstudios.square.interceptor.Interceptor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface RaterInterceptor extends Interceptor {

    void showRaterDialog();

    void showRaterDialogIfNecessary();

    void reset();

}
