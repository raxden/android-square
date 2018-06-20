package com.raxdenstudios.square.interceptor.commons.injectfragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.utils.FragmentUtils;

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
public class InjectFragmentActivityInterceptor<TFragment extends Fragment> extends ActivityInterceptor<InjectFragmentInterceptorCallback<TFragment>> implements InjectFragmentInterceptor {

    private TFragment mContentFragment;

    public InjectFragmentActivityInterceptor(@NonNull Activity activity) {
        super(activity);
    }

    public InjectFragmentActivityInterceptor(@NonNull Activity activity, @NonNull InjectFragmentInterceptorCallback<TFragment> callback) {
        super(activity, callback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mContentFragment != null)
            mContentFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View contentView = mCallback.onLoadFragmentContainer(savedInstanceState);
        if (contentView != null) {
            if (savedInstanceState == null) {
                mContentFragment = mCallback.onCreateFragment();
                FragmentUtils.loadFragment(mActivity.getFragmentManager(), contentView.getId(), mContentFragment);
            } else {
                mContentFragment = (TFragment) FragmentUtils.getFragment(mActivity.getFragmentManager(), contentView.getId());
            }
            mCallback.onFragmentLoaded(mContentFragment);
        }
    }


}
