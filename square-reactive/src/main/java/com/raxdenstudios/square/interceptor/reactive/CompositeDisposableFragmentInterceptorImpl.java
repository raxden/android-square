package com.raxdenstudios.square.interceptor.reactive;

import android.app.Fragment;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.FragmentInterceptor;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public class CompositeDisposableFragmentInterceptorImpl extends FragmentInterceptor<CompositeDisposableInterceptorCallback> implements CompositeDisposableInterceptor {

    private CompositeSubscription mCompositeSubscription;

    public CompositeDisposableFragmentInterceptorImpl(@NonNull Fragment fragment) {
        super(fragment);
        mCompositeSubscription = new CompositeSubscription();
    }

    public CompositeDisposableFragmentInterceptorImpl(@NonNull Fragment fragment, @NonNull CompositeDisposableInterceptorCallback callback) {
        super(fragment, callback);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void addSubscription(Subscription subscription) {
        if (subscription != null && mCompositeSubscription != null) {
            mCompositeSubscription.add(subscription);
        }
    }

    @Override
    public void removeSubscription(Subscription subscription) {
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
            if (mCompositeSubscription != null) {
                mCompositeSubscription.remove(subscription);
            }
        }
    }

    @Override
    public void removeAllSubscritions() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
    }

    @Override
    public boolean hasSubscriptions() {
        if (mCompositeSubscription != null) {
            return mCompositeSubscription.hasSubscriptions();
        } else {
            return false;
        }
    }

}
