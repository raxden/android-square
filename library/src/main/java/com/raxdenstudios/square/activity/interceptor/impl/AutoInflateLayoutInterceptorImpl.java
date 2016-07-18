package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.raxdenstudios.commons.util.ResourceUtils;
import com.raxdenstudios.commons.util.StringUtils;
import com.raxdenstudios.square.activity.InterceptorActivity;
import com.raxdenstudios.square.activity.interceptor.AutoInflateLayoutInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

import java.util.Locale;

/**
 * Created by agomez on 22/05/2015.
 */
public class AutoInflateLayoutInterceptorImpl extends InterceptorActivityImpl implements AutoInflateLayoutInterceptor.AutoInflateLayoutInterceptorCallback {

    private static final String TAG = AutoInflateLayoutInterceptorImpl.class.getSimpleName();

    private AutoInflateLayoutInterceptor mCallbacks;
    private View mInflateLayout;

    public AutoInflateLayoutInterceptorImpl(Activity activity) {
        super(activity);
        if (!(activity instanceof AutoInflateLayoutInterceptor)) {
            throw new IllegalStateException("Activity must implement AutoInflateLayoutInterceptor.");
        }
        mCallbacks = (AutoInflateLayoutInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);

        if (mCallbacks != null && mCallbacks instanceof InterceptorActivity) {
            mInflateLayout = onCreateView(((InterceptorActivity)mCallbacks).getLayoutInflater(), savedInstanceState);
            if (mInflateLayout != null) {
                ((InterceptorActivity)mCallbacks).setContentView(mInflateLayout);
            }
        }

    }

    private View onCreateView(LayoutInflater inflater, Bundle savedInstanceState) {
        View view = null;
        if (mCallbacks != null) {
            view = inflateLayout(inflater);
        }
        return view;
    }

    private View inflateLayout(LayoutInflater inflater) {
        String layoutToSearch = StringUtils.join(StringUtils.uncapitalize(mActivity.getClass().getSimpleName()).split("(?=\\p{Upper})"), "_").toLowerCase(Locale.getDefault());
        int layoutId = ResourceUtils.getLayoutId(getContext(), layoutToSearch);
        if (layoutId > 0) {
            return inflater.inflate(layoutId, null);
        }
        return null;
    }

    @Override
    public View getLayout() {
        return mInflateLayout;
    }
}

