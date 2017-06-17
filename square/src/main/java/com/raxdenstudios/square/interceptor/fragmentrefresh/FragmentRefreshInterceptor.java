package com.raxdenstudios.square.interceptor.fragmentrefresh;

import com.raxdenstudios.square.interceptor.Interceptor;

/**
 * Created by Ángel Gómez on 17/06/2017.
 */

public interface FragmentRefreshInterceptor extends Interceptor {

    void addOnFragmentRefreshListener(OnFragmentRefreshListener listener);

    void removeOnFragmentRefreshListener(OnFragmentRefreshListener listener);


}
