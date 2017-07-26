package com.raxdenstudios.square.interceptor.commons.injectfragmentlist;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

import java.util.List;

/**
 * Created by Ángel Gómez on 20/12/2016.
 */
public interface InjectFragmentListInterceptorCallback<T extends Fragment> extends InterceptorCallback {

    List<View> onLoadFragmentContainer(Bundle savedInstanceState);

    List<T> onCreateFragment();

    void onFragmentLoaded(List<T> fragment);

}
