package com.raxdenstudios.square.interceptor.countback;

import com.raxdenstudios.square.interceptor.Interactor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface CountBackInteractor extends Interactor {

    void setMessage(String message);

    void setRetries(int retries);

}
