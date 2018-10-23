package com.raxdenstudios.square.interceptor.commons.fragmentstatepager;

import android.os.Bundle;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
public interface FragmentStatePagerInterceptorCallback<TFragment extends Fragment> extends InterceptorCallback {

    ViewPager onCreateViewPager(Bundle savedInstanceState);

    void onViewPagerCreated(ViewPager viewPager);

    TFragment onCreateFragment(int position);

    void onFragmentLoaded(TFragment fragment, int position);

    void onFragmentSelected(TFragment fragment, int position);

    int getViewPagerElements();

}
