package com.raxdenstudios.square.interceptor.fragmentstatepager;

import android.app.Fragment;

import com.raxdenstudios.square.interceptor.Interactor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface FragmentStatePagerInteractor<TFragment extends Fragment>
        extends Interactor {

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
