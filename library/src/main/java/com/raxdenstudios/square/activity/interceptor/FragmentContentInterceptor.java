package com.raxdenstudios.square.activity.interceptor;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.callback.FragmentContentInterceptorCallback;

/**
 * Created by agomez on 02/06/2015.
 */
public interface FragmentContentInterceptor<T extends Fragment> extends Interceptor<FragmentContentInterceptorCallback> {

    View onCreateContentFragmentView(Bundle savedInstanceState);

    T onCreateContentFragment();

    void onContentFragmentCreated(T fragment);

}
