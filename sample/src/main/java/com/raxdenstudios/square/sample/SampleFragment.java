package com.raxdenstudios.square.sample;

import android.os.Bundle;

import com.raxdenstudios.square.SquareFragment;
import com.raxdenstudios.square.interceptor.FragmentInterceptor;

import java.util.List;

/**
 * Created by Ángel Gómez on 08/02/2017.
 */

public class SampleFragment extends SquareFragment {

    public static SampleFragment newInstance(Bundle bundle) {
        SampleFragment fragment = new SampleFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void addInterceptor(List<FragmentInterceptor> interceptors) {

    }

}
