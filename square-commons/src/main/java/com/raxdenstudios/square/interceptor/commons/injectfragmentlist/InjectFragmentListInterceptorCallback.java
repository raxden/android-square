package com.raxdenstudios.square.interceptor.commons.injectfragmentlist;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
public interface InjectFragmentListInterceptorCallback<T extends Fragment> extends InterceptorCallback {

    int getFragmentCount();

    View onLoadFragmentContainer(Bundle savedInstanceState, int position);

    T onCreateFragment(int position);

    void onFragmentLoaded(T fragment, int position);

}
