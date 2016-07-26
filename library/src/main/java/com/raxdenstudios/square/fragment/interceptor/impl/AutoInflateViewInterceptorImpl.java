package com.raxdenstudios.square.fragment.interceptor.impl;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.commons.util.ResourceUtils;
import com.raxdenstudios.commons.util.StringUtils;
import com.raxdenstudios.square.fragment.interceptor.AutoInflateViewInterceptor;
import com.raxdenstudios.square.fragment.interceptor.callback.AutoInflateViewInterceptorCallback;
import com.raxdenstudios.square.fragment.interceptor.manager.InterceptorFragmentImpl;

import java.util.Locale;

/**
 * Created by agomez on 02/06/2015.
 */
public class AutoInflateViewInterceptorImpl extends InterceptorFragmentImpl implements AutoInflateViewInterceptorCallback {

    private static final String TAG = AutoInflateViewInterceptorImpl.class.getSimpleName();

    private AutoInflateViewInterceptor mCallbacks;
    private View mInflateView;

    public AutoInflateViewInterceptorImpl(Fragment fragment) {
        super(fragment);

        mCallbacks = (AutoInflateViewInterceptor)fragment;
        mCallbacks.onInterceptorCreated(this);
    }

    @Override
    public View onInterceptorCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflateView =  inflateLayout(inflater);
        return mInflateView;
    }

    private View inflateLayout(LayoutInflater inflater) {
        int layoutId = ResourceUtils.getLayoutId(getContext(), getLayoutName());
        if (layoutId > 0) {
            return inflater.inflate(layoutId, null);
        }
        return null;
    }

    public String getLayoutName() {
        return StringUtils
                .join(StringUtils
                        .uncapitalize(getFragment().getClass().getSimpleName())
                        .split("(?=\\p{Upper})"), "_")
                .toLowerCase(Locale.getDefault());
    }

}
