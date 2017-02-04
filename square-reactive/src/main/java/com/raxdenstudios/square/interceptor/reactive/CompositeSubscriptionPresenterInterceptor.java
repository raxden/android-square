package com.raxdenstudios.square.interceptor.reactive;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;
import com.raxdenstudios.square.interceptor.PresenterInterceptor;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public class CompositeSubscriptionPresenterInterceptor<TView extends IView>
        extends PresenterInterceptor<TView, CompositeSubscriptionInteractor, CompositeSubscriptionInterceptorCallback>
        implements CompositeSubscriptionInteractor {

    private CompositeSubscription mCompositeSubscription;

    public CompositeSubscriptionPresenterInterceptor(Presenter presenter, CompositeSubscriptionInterceptorCallback callback) {
        super(presenter, callback);
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
