package com.raxdenstudios.square.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.adapter.ViewPagerAdapter;

import java.util.List;

/**
 * Created by agomez on 19/03/2015.
 */
public abstract class ViewPagerFragment<T> extends SquareFragment implements
        ViewPagerAdapter.ViewPagerAdapterCallbacks<T>, ViewPager.OnPageChangeListener {

    private static final String TAG = ViewPagerFragment.class.getSimpleName();

    private PagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view.findViewById(R.id.app__viewpager) == null) {
            throw new IllegalStateException("View must have a ViewPager with a @id/app__viewpager");
        }
        viewPager = (ViewPager)view.findViewById(R.id.app__viewpager);
    }

    /**
     * Call this method to init your adapter.
     * @param items
     * @param position
     */
    public void fillAdapter(List<T> items, int position) {
        adapter = new ViewPagerAdapter<T>(items, this);

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(position);
    }

    public PagerAdapter getAdapter() {
        return adapter;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }
}
