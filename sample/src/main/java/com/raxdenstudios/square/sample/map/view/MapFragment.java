package com.raxdenstudios.square.sample.map.view;

import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.square.SquareMVPMapFragment;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.sample.map.presenter.MapPresenter;

import java.util.List;


public class MapFragment extends SquareMVPMapFragment<MapPresenter> implements MapView {

    public static MapFragment newInstance(Bundle bundle) {
        MapFragment fragment = new MapFragment();
//        bundle = bundle == null ? new Bundle() : bundle;
//        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public MapPresenter initializePresenter(Context context) {
        return new MapPresenter(context);
    }

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {

    }

}
