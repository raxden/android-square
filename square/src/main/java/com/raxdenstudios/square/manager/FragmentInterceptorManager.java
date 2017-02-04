package com.raxdenstudios.square.manager;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.interceptor.FragmentInterceptor;
import com.raxdenstudios.square.lifecycle.FragmentLifecycle;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

public class FragmentInterceptorManager extends InterceptorManager<Fragment, FragmentInterceptor>
        implements FragmentLifecycle {

    public FragmentInterceptorManager(Fragment fragment) {
        super(fragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onAttach(activity);
        }
    }

    @Override
    public void onAttach(Context context) {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onAttach(context);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onCreate(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, View inflatedView, ViewGroup container, Bundle savedInstanceState) {
        for (FragmentInterceptor interceptor : interceptors) {
            inflatedView = interceptor.onCreateView(inflater, inflatedView, container, savedInstanceState);
        }
        return inflatedView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onViewCreated(view, savedInstanceState);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onActivityCreated(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onStart();
        }
    }

    @Override
    public void onResume() {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onResume();
        }
    }

    @Override
    public void onPause() {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onPause();
        }
    }

    @Override
    public void onStop() {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onStop();
        }
    }

    @Override
    public void onDestroy() {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onDestroy();
        }
    }

    @Override
    public void onDestroyView() {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onDestroyView();
        }
    }

    @Override
    public void onDetach() {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onDetach();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        for (FragmentInterceptor interceptor : interceptors) {
            interceptor.onConfigurationChanged(configuration);
        }
    }

}
