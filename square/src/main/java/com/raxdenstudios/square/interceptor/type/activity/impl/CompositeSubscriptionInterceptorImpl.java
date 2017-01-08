package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;

import com.raxdenstudios.square.interceptor.callback.CompositeSubscriptionInterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.CompositeSubscriptionInterceptorInteractor;
import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public class CompositeSubscriptionInterceptorImpl
        extends ActivityInterceptor<CompositeSubscriptionInterceptorInteractor, CompositeSubscriptionInterceptorCallback>
        implements CompositeSubscriptionInterceptorInteractor {

    private CompositeSubscription mCompositeSubscription;

    public CompositeSubscriptionInterceptorImpl(Activity activity) {
        super(activity);
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
