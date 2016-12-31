package com.raxdenstudios.square.sample;

import android.os.Bundle;

import com.raxdenstudios.square.SquareActivity;
import com.raxdenstudios.square.interceptor.type.activity.RaterActivityInterceptor;
import com.raxdenstudios.square.interceptor.config.RaterInterceptorConfig;

public class MainActivity extends SquareActivity implements RaterActivityInterceptor {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onInterceptorAttached(RaterInterceptorConfig configuration) {

    }

    @Override
    public void onRaterClickRate() {

    }

    @Override
    public void onRaterClickRemindLater() {

    }

    @Override
    public void onRaterClickDontShowAgain() {

    }

}
