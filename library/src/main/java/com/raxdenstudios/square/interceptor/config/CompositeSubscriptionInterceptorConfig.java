package com.raxdenstudios.square.interceptor.config;

import com.raxdenstudios.square.interceptor.InterceptorConfig;

import rx.Subscription;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface CompositeSubscriptionInterceptorConfig extends InterceptorConfig {

    void addSubscription(Subscription subscription);

    void removeSubscription(Subscription subscription);

}
