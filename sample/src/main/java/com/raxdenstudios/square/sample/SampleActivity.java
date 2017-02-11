package com.raxdenstudios.square.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.raxdenstudios.square.SquareActivity;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.autoinflatelayout.AutoInflateLayoutActivityInterceptor;
import com.raxdenstudios.square.interceptor.autoinflatelayout.AutoInflateLayoutInteractor;
import com.raxdenstudios.square.interceptor.autoinflatelayout.AutoInflateLayoutInterceptorCallback;
import com.raxdenstudios.square.interceptor.butterknife.ButterKnifeActivityInterceptor;
import com.raxdenstudios.square.interceptor.handleextras.HandleExtrasActivityInterceptor;
import com.raxdenstudios.square.interceptor.handleextras.HandleExtrasInteractor;
import com.raxdenstudios.square.interceptor.handleextras.HandleExtrasInterceptorCallback;
import com.raxdenstudios.square.interceptor.injectfragment.InjectFragmentActivityInterceptor;
import com.raxdenstudios.square.interceptor.injectfragment.InjectFragmentInteractor;
import com.raxdenstudios.square.interceptor.injectfragment.InjectFragmentInterceptorCallback;
import com.raxdenstudios.square.interceptor.network.NetworkActivityInterceptor;
import com.raxdenstudios.square.interceptor.network.NetworkInteractor;
import com.raxdenstudios.square.interceptor.network.NetworkInterceptorCallback;

import java.util.List;

import butterknife.BindView;

public class SampleActivity extends SquareActivity {

    @BindView(R.id.content)
    FrameLayout mContentView;

    private SampleFragment mSampleFragment;

    @Override
    protected void addInterceptor(List<ActivityInterceptor> interceptors) {
        interceptors.add(new AutoInflateLayoutActivityInterceptor(this, new AutoInflateLayoutInterceptorCallback() {
            @Override
            public void onContentViewCreated(View view, Bundle savedInstanceState) {

            }

            @Override
            public void onInterceptorAttached(AutoInflateLayoutInteractor interactor) {

            }
        }));
        interceptors.add(new HandleExtrasActivityInterceptor(this, new HandleExtrasInterceptorCallback() {
            @Override
            public void onHandleExtras(Bundle savedInstanceState, Bundle extras) {

            }

            @Override
            public void onInterceptorAttached(HandleExtrasInteractor interactor) {

            }
        }));
        interceptors.add(new ButterKnifeActivityInterceptor(this));
        interceptors.add(new NetworkActivityInterceptor(this, new NetworkInterceptorCallback() {
            @Override
            public void onWifiAvailable(boolean available) {

            }

            @Override
            public void onNetworkAvailable(boolean available) {

            }

            @Override
            public void onInterceptorAttached(NetworkInteractor interactor) {

            }
        }));
        interceptors.add(new InjectFragmentActivityInterceptor<SampleFragment>(this, new InjectFragmentInterceptorCallback<SampleFragment>() {
            @Override
            public View onLoadFragmentContainer(Bundle savedInstanceState) {
                return mContentView;
            }

            @Override
            public SampleFragment onCreateFragment() {
                return SampleFragment.newInstance(getIntent().getExtras());
            }

            @Override
            public void onFragmentLoaded(SampleFragment fragment) {
                mSampleFragment = fragment;
            }

            @Override
            public void onInterceptorAttached(InjectFragmentInteractor interactor) {

            }
        }));
    }

}