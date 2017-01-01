package com.raxdenstudios.square.interceptor.type.presenter.impl;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;
import com.raxdenstudios.square.interceptor.type.PresenterInterceptor;
import com.raxdenstudios.square.interceptor.callback.CompositeSubscriptionInterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.CompositeSubscriptionInterceptorInteractor;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public class CompositeSubscriptionInterceptorImpl<TView extends IView>
        extends PresenterInterceptor<TView, CompositeSubscriptionInterceptorInteractor, CompositeSubscriptionInterceptorCallback>
        implements CompositeSubscriptionInterceptorInteractor {

    private CompositeSubscription mCompositeSubscription;

    public CompositeSubscriptionInterceptorImpl(Presenter presenter) {
        super(presenter);
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

}
