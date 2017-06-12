package com.raxdenstudios.square.interceptor.icepick;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;
import com.raxdenstudios.square.interceptor.PresenterInterceptor;

import icepick.Icepick;

/**
 * Created by Ángel Gómez on 11/02/2017.
 */

public class IcepickPresenterInterceptor<TView extends IView>
        extends PresenterInterceptor<TView, IcepickInteractor, IcepickInterceptorCallback>
        implements IcepickInteractor {

    public IcepickPresenterInterceptor(@NonNull Presenter<TView> presenter) {
        super(presenter);
    }

    public IcepickPresenterInterceptor(@NonNull Presenter<TView> presenter, @NonNull IcepickInterceptorCallback callback) {
        super(presenter, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onSave(Bundle outState) {
        super.onSave(outState);
        Icepick.saveInstanceState(this, outState);
    }

}
