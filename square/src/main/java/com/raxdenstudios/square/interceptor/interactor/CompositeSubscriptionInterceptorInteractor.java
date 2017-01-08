package com.raxdenstudios.square.interceptor.interactor;

import com.raxdenstudios.square.interceptor.InterceptorInteractor;

import rx.Subscription;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface CompositeSubscriptionInterceptorInteractor extends InterceptorInteractor {

    void addSubscription(Subscription subscription);

    void removeSubscription(Subscription subscription);

    void removeAllSubscritions();

    boolean hasSubscriptions();

}
