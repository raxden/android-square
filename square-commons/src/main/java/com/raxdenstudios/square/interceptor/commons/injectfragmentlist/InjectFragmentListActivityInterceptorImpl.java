package com.raxdenstudios.square.interceptor.commons.injectfragmentlist;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.utils.FragmentUtils;

import java.util.ArrayList;
import java.util.List;

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

        List<View> mContentViewList = mCallback.onLoadFragmentContainer(savedInstanceState);
        if (mContentViewList != null && !mContentViewList.isEmpty()) {
            List<TFragment> contentFragmentList = new ArrayList<>();
            if (savedInstanceState == null) {
                contentFragmentList = mCallback.onCreateFragment();
                if (mContentViewList.size() != contentFragmentList.size()) {
                    throw new IllegalStateException("You must provide the same number of views as fragments");
                }
                for (int i = 0; i < mContentViewList.size(); i++) {
                    View view = mContentViewList.get(i);
                    TFragment fragment = contentFragmentList.get(i);
                    FragmentUtils.loadFragment(mActivity.getFragmentManager(), view.getId(), fragment);
                }
            } else {
                for (int i = 0; i < mContentViewList.size(); i++) {
                    View view = mContentViewList.get(i);
                    contentFragmentList.add((TFragment) FragmentUtils.getFragment(mActivity.getFragmentManager(), view.getId()));
                }
            }
            mCallback.onFragmentLoaded(contentFragmentList);
        }
    }


}
