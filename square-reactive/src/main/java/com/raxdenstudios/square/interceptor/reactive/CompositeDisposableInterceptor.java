package com.raxdenstudios.square.interceptor.reactive;

import com.raxdenstudios.square.interceptor.Interceptor;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface CompositeDisposableInterceptor extends Interceptor {

    void addSubscriber(ResourceSubscriber subscriber);

    void removeSubscriber(ResourceSubscriber subscriber);

    void removeAllSubscribers();

    boolean hasSubscribers();

}
