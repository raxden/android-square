package com.raxdenstudios.square.view.interceptor.manager;


import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import com.raxdenstudios.square.view.interceptor.IInterceptorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raxden on 18/07/2016.
 */
public class InterceptorViewManager {

    private static final String TAG = InterceptorViewManager.class.getSimpleName();

    private List<IInterceptorView> interceptors;

    public InterceptorViewManager(View view) {
        if (view != null) initInterceptors(view);
    }

    public void onAttachedToWindowInterceptors() {
        if (interceptors != null) {
            for (IInterceptorView interceptor : interceptors) {
                interceptor.onAttachedToWindow();
            }
        }
    }

    public void onMeasureInterceptors(int widthMeasureSpec, int heightMeasureSpec) {
        if (interceptors != null) {
            for (IInterceptorView interceptor : interceptors) {
                interceptor.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
    }
    
    public void onLayoutInterceptors(boolean changed, int left, int top, int right, int bottom) {
        if (interceptors != null) {
            for (IInterceptorView interceptor : interceptors) {
                interceptor.onLayout(changed, left, top, right, bottom);
            }
        }
    }
    
    public void dispatchDrawInterceptors(Canvas canvas) {
        if (interceptors != null) {
            for (IInterceptorView interceptor : interceptors) {
                interceptor.dispatchDraw(canvas);
            }
        }
    }
    
    public void drawInterceptors(Canvas canvas) {
        if (interceptors != null) {
            for (IInterceptorView interceptor : interceptors) {
                interceptor.draw(canvas);
            }
        }
    }
    
    public void onDrawInterceptors(Canvas canvas) {
        if (interceptors != null) {
            for (IInterceptorView interceptor : interceptors) {
                interceptor.onDraw(canvas);
            }
        }
    }
    
    public void onDetachedFromWindowInterceptors() {
        if (interceptors != null) {
            for (IInterceptorView interceptor : interceptors) {
                interceptor.onDetachedFromWindow();
            }
        }
    }
    
    public void addInterceptor(IInterceptorView interceptor) {
        interceptors.add(interceptor);
    }

    public List<IInterceptorView> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<IInterceptorView> interceptors) {
        this.interceptors = interceptors;
    }

    private void initInterceptors(View view) {
        interceptors = new ArrayList<>();
        Log.d(TAG, "========== Prepare to init view interceptors ==========");
      
        Log.d(TAG, "======================================================");
    }
    
}
