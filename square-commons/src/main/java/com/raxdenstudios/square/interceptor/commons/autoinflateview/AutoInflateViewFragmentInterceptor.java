package com.raxdenstudios.square.interceptor.commons.autoinflateview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.interceptor.FragmentInterceptor;
import com.raxdenstudios.square.utils.ResourceUtils;
import com.raxdenstudios.square.utils.StringUtils;

import java.util.Locale;

/**
 * Created by agomez on 02/06/2015.
 */
public class AutoInflateViewFragmentInterceptor extends FragmentInterceptor<AutoInflateViewInterceptorCallback> implements AutoInflateViewInterceptor {

    private int mLayoutId;

    public AutoInflateViewFragmentInterceptor(@NonNull Fragment fragment) {
        super(fragment);
    }

    public AutoInflateViewFragmentInterceptor(@NonNull Fragment fragment, @NonNull AutoInflateViewInterceptorCallback callback) {
        super(fragment, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, View inflatedView,  ViewGroup container, Bundle savedInstanceState) {
        return inflateLayout(inflater);
    }

    private View inflateLayout(LayoutInflater inflater) {
        if (mLayoutId != 0) {
            return inflater.inflate(mLayoutId, null);
        } else {
            int layoutId = ResourceUtils.getLayoutId(mContext, getLayoutName());
            if (layoutId > 0) {
                return inflater.inflate(layoutId, null);
            }
        }
        return null;
    }

    private String getLayoutName() {
        String className = StringUtils.uncapitalize(mFragment.getClass().getSimpleName());
        return StringUtils.join(className.split("(?=\\p{Upper})"), "_").toLowerCase(Locale.getDefault());
    }

    @Override
    public void setLayoutId(int layoutId) {
        mLayoutId = layoutId;
    }

}
