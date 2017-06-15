package com.raxdenstudios.square.interceptor.reactive;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public class CompositeSubscriptionActivityInterceptorImpl extends ActivityInterceptor<CompositeSubscriptionInterceptorCallback> implements CompositeSubscriptionInterceptor {

    private CompositeSubscription mCompositeSubscription;

    public CompositeSubscriptionActivityInterceptorImpl(@NonNull Activity activity) {
        super(activity);
        mCompositeSubscription = new CompositeSubscription();
    }

    public CompositeSubscriptionActivityInterceptorImpl(@NonNull Activity activity, @NonNull CompositeSubscriptionInterceptorCallback callback) {
        super(activity, callback);
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
