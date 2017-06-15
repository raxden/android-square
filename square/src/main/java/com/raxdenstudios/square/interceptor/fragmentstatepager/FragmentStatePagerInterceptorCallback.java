package com.raxdenstudios.square.interceptor.fragmentstatepager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
public interface FragmentStatePagerInterceptorCallback<TFragment extends Fragment> extends InterceptorCallback {

    ViewPager onCreateViewPager(Bundle savedInstanceState);

    TFragment onCreatePagerContentFragment(int position);

    int onViewPagerElements();

}
