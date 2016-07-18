package com.raxdenstudios.square.view.interceptor;

import android.graphics.Canvas;

/**
 * Created by Raxden on 18/07/2016.
 */
public interface IInterceptorView {

    void onAttachedToWindow();

    void onMeasure(int widthMeasureSpec, int heightMeasureSpec);

    void onLayout(boolean changed, int left, int top, int right, int bottom);

    void dispatchDraw(Canvas canvas);

    void draw(Canvas canvas);

    void onDraw(Canvas canvas);

    void onDetachedFromWindow();

}
