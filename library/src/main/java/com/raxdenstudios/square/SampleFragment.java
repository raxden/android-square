package com.raxdenstudios.square;

import android.content.Context;

import com.raxdenstudios.square.activity.interceptor.TimerInterceptor;
import com.raxdenstudios.square.activity.interceptor.callback.TimerInterceptorCallback;

/**
 * Created by Raxden on 26/07/2016.
 */
public class SampleFragment extends BaseFragment<BasePresenter> implements TimerInterceptor {

    @Override
    public BasePresenter initializePresenter(Context context) {
        return null;
    }

    @Override
    public void onTimerEnd() {

    }

    @Override
    public void onTimerCancelled() {

    }

    @Override
    public void onInterceptorCreated(TimerInterceptorCallback callback) {

    }

}
