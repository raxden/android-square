package com.raxdenstudios.square.interceptor.commons.injectfragmentlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.utils.FragmentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
public class InjectFragmentListActivityInterceptor<TFragment extends Fragment> extends ActivityInterceptor<InjectFragmentListInterceptorCallback<TFragment>> implements InjectFragmentListInterceptor {

    List<TFragment> mFragmentList;

    public InjectFragmentListActivityInterceptor(@NonNull FragmentActivity activity) {
        super(activity);
    }

    public InjectFragmentListActivityInterceptor(@NonNull FragmentActivity activity, @NonNull InjectFragmentListInterceptorCallback<TFragment> callback) {
        super(activity, callback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mFragmentList != null && !mFragmentList.isEmpty()) {
            for (TFragment fragment : mFragmentList) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < getCallback().getFragmentCount(); i++) {
            View contentView = getCallback().onLoadFragmentContainer(savedInstanceState, i);
            TFragment contentFragment;
            if (savedInstanceState == null) {
                contentFragment = getCallback().onCreateFragment(i);
                FragmentUtils.INSTANCE.loadFragment(getActivity().getSupportFragmentManager(), contentView.getId(), contentFragment);
            } else {
                contentFragment = (TFragment) FragmentUtils.INSTANCE.getFragment(getActivity().getSupportFragmentManager(), contentView.getId());
            }
            getCallback().onFragmentLoaded(contentFragment, i);
            addFragment(contentFragment);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragmentList.clear();
        mFragmentList = null;
    }

    private void addFragment(TFragment fragment) {
        if (mFragmentList == null) mFragmentList = new ArrayList<>();
        mFragmentList.add(fragment);
    }

}
