package com.raxdenstudios.square.interceptor.timer;

import com.raxdenstudios.square.interceptor.Interactor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface TimerInteractor extends Interactor {

    void setTime(long milliseconds);

    void setTime(int seconds);

}
