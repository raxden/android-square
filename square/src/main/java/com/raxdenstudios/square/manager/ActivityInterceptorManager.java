package com.raxdenstudios.square.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.lifecycle.ActivityLifecycle;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

public class ActivityInterceptorManager extends InterceptorManager<Activity, ActivityInterceptor> implements ActivityLifecycle {

    public ActivityInterceptorManager(Activity activity) {
        super(activity);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        for (ActivityInterceptor interceptor : interceptors) {
            interceptor.onSaveInstanceState(outState);
        }
    }

    @Override
    public Context attachBaseContext(Context newBase) {
        for (ActivityInterceptor interceptor : interceptors) {
            newBase = interceptor.attachBaseContext(newBase);
        }
        return newBase;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        for (ActivityInterceptor interceptor : interceptors) {
            interceptor.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        for (ActivityInterceptor interceptor : interceptors) {
            interceptor.onPostCreate(savedInstanceState);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu) {
        for (ActivityInterceptor interceptor : interceptors) {
            interceptor.onCreateOptionsMenu(menu);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        for (ActivityInterceptor interceptor : interceptors) {
            interceptor.onPrepareOptionsMenu(menu);
        }
    }

    @Override
    public void onStart() {
        for (ActivityInterceptor interceptor : interceptors) {
            interceptor.onStart();
        }
    }

    @Override
    public void onResume() {
        for (ActivityInterceptor interceptor : interceptors) {
            interceptor.onResume();
        }
    }

    @Override
    public void onPause() {
        for (ActivityInterceptor interceptor : interceptors) {
            interceptor.onPause();
        }
    }

    @Override
    public void onStop() {
        for (ActivityInterceptor interceptor : interceptors) {
            interceptor.onStop();
        }
    }

    @Override
    public void onDestroy() {
        for (ActivityInterceptor interceptor : interceptors) {
            interceptor.onDestroy();
        }
    }

    @Override
    public boolean onBackPressed() {
        for (ActivityInterceptor interceptor : interceptors) {
            if (interceptor.onBackPressed()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (ActivityInterceptor interceptor : interceptors) {
            interceptor.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        for (ActivityInterceptor interceptor : interceptors) {
            interceptor.onConfigurationChanged(configuration);
        }
    }

}
