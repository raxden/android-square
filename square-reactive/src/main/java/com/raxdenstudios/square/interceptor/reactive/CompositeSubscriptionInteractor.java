package com.raxdenstudios.square.interceptor.reactive;

import com.raxdenstudios.square.interceptor.Interactor;

import rx.Subscription;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface CompositeSubscriptionInteractor extends Interactor {

    void addSubscription(Subscription subscription);

    void removeSubscription(Subscription subscription);

    void removeAllSubscritions();

    boolean hasSubscriptions();

}
