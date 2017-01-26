package com.raxdenstudios.square.sample;

import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.SquareActivity;
import com.raxdenstudios.square.interceptor.interactor.AutoInflateLayoutInterceptorInteractor;
import com.raxdenstudios.square.interceptor.type.activity.AutoInflateLayoutActivityInterceptor;

public class AutoInflateLayoutActivity extends SquareActivity implements AutoInflateLayoutActivityInterceptor {

    AutoInflateLayoutInterceptorInteractor mAutoInflateLayoutInteractor;

    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onContentViewCreated(View view, Bundle savedInstanceState) {
        mView = view;
    }

    @Override
    public void onInterceptorAttached(AutoInflateLayoutInterceptorInteractor interceptor) {
        mAutoInflateLayoutInteractor = interceptor;
    }


}