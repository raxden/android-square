package com.raxdenstudios.square.interceptor.commons.injectfragment;

import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

import androidx.fragment.app.Fragment;

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
public interface InjectFragmentInterceptorCallback<T extends Fragment> extends InterceptorCallback {

    View onLoadFragmentContainer(Bundle savedInstanceState);

    T onCreateFragment();

    void onFragmentLoaded(T fragment);

}
