package com.raxdenstudios.square.view.interceptor;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.raxdenstudios.square.view.InterceptorView;

/**
 * Created by Raxden on 18/07/2016.
 */
public class InterceptorViewImpl implements IInterceptorView {

    protected View mView;

    public InterceptorViewImpl(View view) {
        mView = view;
        checkContextIfInterceptorViewInstance(view);
    }

    @Override
    public void onAttachedToWindow() {

    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {

    }

    @Override
    public void dispatchDraw(Canvas canvas) {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void onDraw(Canvas canvas) {

    }

    @Override
    public void onDetachedFromWindow() {

    }

    public Context getContext() {
        return mView.getContext();
    }

    private void checkContextIfInterceptorViewInstance(View view) {
        if (!(view instanceof InterceptorView)) {
            throw new IllegalStateException(this.getClass().getSimpleName()+" interceptor must be used just on IInterceptorView");
        }
    }

}
