package com.raxdenstudios.square;

import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.mvp.presenter.IPresenter;
import com.raxdenstudios.square.fragment.InterceptorMVPFragment;
import com.raxdenstudios.square.fragment.interceptor.AutoInflateViewInterceptor;
import com.raxdenstudios.square.fragment.interceptor.BundleArgumentsInterceptor;
import com.raxdenstudios.square.fragment.interceptor.callback.AutoInflateViewInterceptorCallback;
import com.raxdenstudios.square.fragment.interceptor.callback.BundleArgumentsInterceptorCallback;

/**
 * Created by Raxden on 26/07/2016.
 */
public abstract class BaseFragment<TPresenter extends IPresenter> extends InterceptorMVPFragment<TPresenter>
    implements BundleArgumentsInterceptor, AutoInflateViewInterceptor {

    @Override
    public void onHandleArguments(Bundle savedInstanceState, Bundle arguments) {

    }

    @Override
    public void onInterceptorCreated(BundleArgumentsInterceptorCallback callback) {

    }

    @Override
    public void onInterceptorCreated(AutoInflateViewInterceptorCallback callback) {

    }

    @Override
    public TPresenter initializePresenter(Context context) {
        return null;
    }
}
