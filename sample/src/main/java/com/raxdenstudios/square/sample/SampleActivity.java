package com.raxdenstudios.square.sample;

import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.SquareActivity;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback;

import java.util.List;

public class SampleActivity extends SquareActivity implements AutoInflateLayoutInterceptorCallback {

    @Override
    public void onContentViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        AutoInflateLayoutInterceptor autoInflateLayoutInterceptor = new AutoInflateLayoutActivityInterceptor(this, this);

        interceptorList.add(autoInflateLayoutInterceptor);
    }

}