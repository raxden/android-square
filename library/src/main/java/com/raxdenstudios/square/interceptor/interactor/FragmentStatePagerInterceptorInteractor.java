package com.raxdenstudios.square.interceptor.interactor;

import android.app.Fragment;

import com.raxdenstudios.square.interceptor.InterceptorInteractor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface FragmentStatePagerInterceptorInteractor<TFragment extends Fragment>
        extends InterceptorInteractor {

    boolean isFirstPage();

    boolean isLastPage();

    int getNumPages();

    int getCurrentPosition();

    void setCurrentPage(int page);

    void setCurrentPage(int page, boolean smoothScroll);

    TFragment getCurrentPage();

    TFragment nextPage();

    TFragment previousPage();

}
