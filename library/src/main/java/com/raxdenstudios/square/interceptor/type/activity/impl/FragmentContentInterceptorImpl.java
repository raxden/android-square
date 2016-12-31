package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.commons.util.FragmentUtils;
import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.callback.FragmentContentInterceptorCallback;
import com.raxdenstudios.square.interceptor.config.FragmentContentInterceptorConfig;

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
public class FragmentContentInterceptorImpl<TFragment extends Fragment>
        extends ActivityInterceptor<FragmentContentInterceptorConfig, FragmentContentInterceptorCallback<TFragment>>
        implements FragmentContentInterceptorConfig {

    public FragmentContentInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View mContentView = mCallback.onCreateContentFragmentView(savedInstanceState);
        if (mContentView != null) {
            TFragment contentFragment;
            if (savedInstanceState == null) {
                contentFragment = mCallback.onCreateContentFragment();
                FragmentUtils.loadFragment(mActivity, mContentView.getId(), contentFragment);
            } else {
                contentFragment = (TFragment) FragmentUtils.getFragment(mActivity, mContentView.getId());
            }
            mCallback.onContentFragmentCreated(contentFragment);
        }
    }

}
