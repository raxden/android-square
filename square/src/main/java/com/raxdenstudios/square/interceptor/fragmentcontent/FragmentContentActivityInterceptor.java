package com.raxdenstudios.square.interceptor.fragmentcontent;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.commons.util.FragmentUtils;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
public class FragmentContentActivityInterceptor<TFragment extends Fragment>
        extends ActivityInterceptor<FragmentContentInteractor, FragmentContentInterceptorCallback<TFragment>>
        implements FragmentContentInteractor {

    public FragmentContentActivityInterceptor(Activity activity, FragmentContentInterceptorCallback callback) {
        super(activity, callback);
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
