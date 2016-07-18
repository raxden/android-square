package com.raxdenstudios.square.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import com.raxdenstudios.square.view.interceptor.IInterceptorView;
import com.raxdenstudios.square.view.interceptor.manager.InterceptorViewManager;

import java.util.List;

/**
 * Created by Raxden on 18/07/2016.
 */
public class InterceptorView extends View {

    private static final String TAG = InterceptorView.class.getSimpleName();

    /* Interceptor Manager */
    private InterceptorViewManager mInterceptorManager;

    public InterceptorView(Context context) {
        super(context);
    }

    public InterceptorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        super.onSaveInstanceState();
        return getInterceptorManager().onSaveInstanceStateInterceptors();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        getInterceptorManager().onRestoreInstanceStateInterceptors(state);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getInterceptorManager().onAttachedToWindowInterceptors();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getInterceptorManager().onMeasureInterceptors(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        getInterceptorManager().onLayoutInterceptors(changed, left, top, right, bottom);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        getInterceptorManager().dispatchDrawInterceptors(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        getInterceptorManager().drawInterceptors(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getInterceptorManager().onDrawInterceptors(canvas);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getInterceptorManager().onDetachedFromWindowInterceptors();
    }

        /* Support methods */

    private InterceptorViewManager getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = new InterceptorViewManager(this);
            addInterceptor(mInterceptorManager.getInterceptors());
        }
        return mInterceptorManager;
    }

    protected void addInterceptor(List<IInterceptorView> interceptors) {

    }

    protected List<IInterceptorView> getInterceptors() {
        return mInterceptorManager.getInterceptors();
    }

}
