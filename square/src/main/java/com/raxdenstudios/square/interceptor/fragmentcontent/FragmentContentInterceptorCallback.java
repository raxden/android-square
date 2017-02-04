package com.raxdenstudios.square.interceptor.fragmentcontent;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
public interface FragmentContentInterceptorCallback<T extends Fragment>
        extends InterceptorCallback<FragmentContentInteractor> {

    View onCreateContentFragmentView(Bundle savedInstanceState);

    T onCreateContentFragment();

    void onContentFragmentCreated(T fragment);

}
