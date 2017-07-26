package com.raxdenstudios.square.interceptor.commons.injectfragmentlist;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.utils.FragmentUtils;

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
public class InjectFragmentListActivityInterceptorImpl<TFragment extends Fragment> extends ActivityInterceptor<InjectFragmentListInterceptorCallback<TFragment>> implements InjectFragmentListInterceptor {

    public InjectFragmentListActivityInterceptorImpl(@NonNull Activity activity) {
        super(activity);
    }

    public InjectFragmentListActivityInterceptorImpl(@NonNull Activity activity, @NonNull InjectFragmentListInterceptorCallback<TFragment> callback) {
        super(activity, callback);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < mCallback.getFragmentCount(); i++) {
            View contentView = mCallback.onLoadFragmentContainer(savedInstanceState, i);
            TFragment contentFragment;
            if (savedInstanceState == null) {
                contentFragment = mCallback.onCreateFragment(i);
                FragmentUtils.loadFragment(mActivity.getFragmentManager(), contentView.getId(), contentFragment);
            } else {
                contentFragment = (TFragment) FragmentUtils.getFragment(mActivity.getFragmentManager(), contentView.getId());
            }
            mCallback.onFragmentLoaded(contentFragment, i);
        }
    }


}
