package com.raxdenstudios.square.interceptor.fragmentbackpressed;

import com.raxdenstudios.square.interceptor.Interceptor;

/**
 * Created by Ángel Gómez on 17/06/2017.
 */

public interface FragmentBackPressedInterceptor extends Interceptor {

    void addOnFragmentBackPressedListener(OnFragmentBackPressedListener listener);

    void removeOnFragmentBackPressedListener(OnFragmentBackPressedListener listener);

}
