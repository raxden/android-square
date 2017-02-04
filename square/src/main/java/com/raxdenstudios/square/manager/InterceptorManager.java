package com.raxdenstudios.square.manager;

import com.raxdenstudios.square.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ángel Gómez on 15/07/2016.
 */
public abstract class InterceptorManager<T, I extends Interceptor> {

    T type;
    List<I> interceptors;

    public InterceptorManager(T type) {
        this.type = type;
        this.interceptors = new ArrayList<>();
        loadInterceptors(type);
    }

    public void addInterceptor(I interceptor) {
        interceptors.add(interceptor);
    }

    public List<I> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<I> interceptors) {
        this.interceptors = interceptors;
    }

    protected void loadInterceptors(T type) {
        for (Interceptor interceptor : interceptors) {
            this.interceptors.add((I) interceptor);
        }
    }

}
