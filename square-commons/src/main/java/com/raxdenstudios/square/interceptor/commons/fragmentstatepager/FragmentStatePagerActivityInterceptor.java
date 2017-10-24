package com.raxdenstudios.square.interceptor.commons.fragmentstatepager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
public class FragmentStatePagerActivityInterceptor<TFragment extends Fragment> extends ActivityInterceptor<FragmentStatePagerInterceptorCallback<TFragment>> implements FragmentStatePagerInterceptor<TFragment> {

    private ViewPager mViewPager;
    private FragmentStatePagerInterceptorAdapter mAdapter;

    public FragmentStatePagerActivityInterceptor(@NonNull Activity activity) {
        super(activity);
    }

    public FragmentStatePagerActivityInterceptor(@NonNull Activity activity, @NonNull FragmentStatePagerInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = mCallback.onCreateViewPager(savedInstanceState);
        if (mViewPager != null) {
            mAdapter = new FragmentStatePagerInterceptorAdapter(mActivity.getFragmentManager());
            mViewPager.setAdapter(mAdapter);
            mViewPager.addOnPageChangeListener(onPageChangeListener);
            mCallback.onViewPagerCreated(mViewPager);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mViewPager != null) {
            mViewPager.removeOnPageChangeListener(onPageChangeListener);
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
        return mAdapter.getCount();
    }

    @Override
    public int getCurrentPosition() {
        return mViewPager.getCurrentItem();
    }

    @Override
    public TFragment getCurrentFragment() {
        return mAdapter.getFragment(getCurrentPosition());
    }

    @Override
    public TFragment nextPage() {
        if (!isLastPage()) {
            mViewPager.setCurrentItem(getCurrentPosition() + 1);
            return getCurrentFragment();
        } else {
            return null;
        }
    }

    @Override
    public TFragment previousPage() {
        if (!isFirstPage()) {
            mViewPager.setCurrentItem(getCurrentPosition() - 1);
            return getCurrentFragment();
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
        return getCurrentPosition() == getNumPages();
    }

    private class FragmentStatePagerInterceptorAdapter extends FragmentStatePagerAdapter {

        FragmentStatePagerInterceptorAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public TFragment getItem(int position) {
            return mCallback.onCreateFragment(position);
        }

        @Override
        public int getCount() {
            return mCallback.getViewPagerElements();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TFragment fragment = (TFragment) super.instantiateItem(container, position);
            mCallback.onFragmentLoaded(fragment, position);
            return fragment;
        }

        public TFragment getFragment(int position) {
            return (TFragment) instantiateItem(mViewPager, position);
        }

    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            TFragment fragment = mAdapter.getFragment(position);
            mCallback.onFragmentSelected(fragment, position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
