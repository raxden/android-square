package com.raxdenstudios.square.sample;

import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.SquareActivity;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.autoinflatelayout.AutoInflateLayoutActivityInterceptor;
import com.raxdenstudios.square.interceptor.autoinflatelayout.AutoInflateLayoutInteractor;
import com.raxdenstudios.square.interceptor.autoinflatelayout.AutoInflateLayoutInterceptorCallback;

import java.util.List;

public class SampleActivity extends SquareActivity {

    @Override
    protected void addInterceptor(List<ActivityInterceptor> interceptors) {
        AutoInflateLayoutActivityInterceptor interceptor = new AutoInflateLayoutActivityInterceptor(this, new AutoInflateLayoutInterceptorCallback() {
            @Override
            public void onContentViewCreated(View view, Bundle savedInstanceState) {

            }

            @Override
            public void onInterceptorAttached(AutoInflateLayoutInteractor interactor) {

            }
        });
        interceptors.add(interceptor);
    }

}