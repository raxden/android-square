package com.raxdenstudios.square.interceptor.rater;

import com.raxdenstudios.square.interceptor.Interactor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface RaterInteractor extends Interactor {

    void showRaterDialog();

    void showRaterDialogIfNecessary();

    void reset();

}
