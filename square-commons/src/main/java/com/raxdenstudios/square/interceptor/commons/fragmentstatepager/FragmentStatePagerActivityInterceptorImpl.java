package com.raxdenstudios.square.interceptor.commons.fragmentstatepager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
public class FragmentStatePagerActivityInterceptorImpl<TFragment extends Fragment> extends ActivityInterceptor<FragmentStatePagerInterceptorCallback<TFragment>> implements FragmentStatePagerInterceptor<TFragment> {

    private ViewPager mViewPager;
    private FragmentStatePagerInterceptorAdapter mAdapter;

    public FragmentStatePagerActivityInterceptorImpl(@NonNull Activity activity) {
        super(activity);
    }

    public FragmentStatePagerActivityInterceptorImpl(@NonNull Activity activity, @NonNull FragmentStatePagerInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = mCallback.onCreateViewPager(savedInstanceState);
        if (mViewPager != null) {
            mAdapter = new FragmentStatePagerInterceptorAdapter(mActivity.getFragmentManager());
            mViewPager.setAdapter(mAdapter);
        }
    }

    @Override
    public void setCurrentPage(int page) {
        mViewPager.setCurrentItem(page);
    }

    @Override
    public void setCurrentPage(int page, boolean smoothScroll) {
        mViewPager.setCurrentItem(page, smoothScroll);
    }

    @Override
    public int getNumPages() {
        return mViewPager.getChildCount();
    }

    @Override
    public int getCurrentPosition() {
        return mViewPager.getCurrentItem();
    }

    @Override
    public TFragment getCurrentPage() {
        return mAdapter.getFragment(getCurrentPosition());
    }

    @Override
    public TFragment nextPage() {
        if (!isLastPage()) {
            mViewPager.setCurrentItem(getCurrentPosition() + 1);
            return getCurrentPage();
        } else {
            return null;
        }
    }

    @Override
    public TFragment previousPage() {
        if (!isFirstPage()) {
            mViewPager.setCurrentItem(getCurrentPosition() - 1);
            return getCurrentPage();
        } else {
            return null;
        }
    }

    @Override
    public boolean isFirstPage() {
        return getCurrentPosition() == 0;
    }

    @Override
    public boolean isLastPage() {
        return getCurrentPosition() == getNumPages() - 1;
    }

    private class FragmentStatePagerInterceptorAdapter extends FragmentStatePagerAdapter {

        FragmentStatePagerInterceptorAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public TFragment getItem(int position) {
            return mCallback.onCreatePagerContentFragment(position);
        }

        @Override
        public int getCount() {
            return mCallback.onViewPagerElements();
        }

        public TFragment getFragment(int position) {
            return (TFragment) instantiateItem(mViewPager, position);
        }

    }
}
