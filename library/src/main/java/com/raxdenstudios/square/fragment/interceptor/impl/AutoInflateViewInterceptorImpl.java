package com.raxdenstudios.square.fragment.interceptor.impl;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.commons.util.ResourceUtils;
import com.raxdenstudios.commons.util.StringUtils;
import com.raxdenstudios.square.fragment.InterceptorFragment;
import com.raxdenstudios.square.fragment.interceptor.AutoInflateViewInterceptor;
import com.raxdenstudios.square.fragment.interceptor.manager.InterceptorFragmentImpl;

import java.util.Locale;

/**
 * Created by agomez on 02/06/2015.
 */
public class AutoInflateViewInterceptorImpl extends InterceptorFragmentImpl implements AutoInflateViewInterceptor.AutoInflateViewInterceptorCallback {

    private static final String TAG = AutoInflateViewInterceptorImpl.class.getSimpleName();

    private AutoInflateViewInterceptor mCallbacks;
    private View mInflateView;

    public AutoInflateViewInterceptorImpl(Fragment fragment) {
        super(fragment);
        if (!(fragment instanceof AutoInflateViewInterceptor)) {
            throw new IllegalStateException("Fragment must implement AutoInflateViewInterceptor.");
        }
        mCallbacks = (AutoInflateViewInterceptor)fragment;
    }

    @Override
    public View onInterceptorCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflateView =  inflateLayout(inflater);
        return mInflateView;
    }

    private View inflateLayout(LayoutInflater inflater) {
        String layoutToSearch = StringUtils.join(StringUtils.uncapitalize(((InterceptorFragment)mCallbacks).getClass().getSimpleName()).split("(?=\\p{Upper})"), "_").toLowerCase(Locale.getDefault());
        int layoutId = ResourceUtils.getLayoutId(getContext(), layoutToSearch);
        if (layoutId > 0) {
            return inflater.inflate(layoutId, null);
        } else {
            Log.w(TAG, layoutToSearch+" not found!");
        }
        return null;
    }

    @Override
    public View getView() {
        return mInflateView;
    }

}
