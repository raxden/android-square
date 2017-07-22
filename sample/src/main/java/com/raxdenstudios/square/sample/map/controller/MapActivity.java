package com.raxdenstudios.square.sample.map.controller;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.raxdenstudios.square.SquareActivity;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptorImpl;
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentActivityInterceptorImpl;
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback;
import com.raxdenstudios.square.sample.R;

import java.util.List;

public class MapActivity extends SquareActivity implements
        InjectFragmentInterceptorCallback<MapFragment>,
        AutoInflateLayoutInterceptorCallback,
        OnMapReadyCallback {

    private MapFragment mapFragment;

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onContentViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public View onLoadFragmentContainer(Bundle savedInstanceState) {
        return findViewById(R.id.content);
    }

    @Override
    public MapFragment onCreateFragment() {
        return MapFragment.newInstance();
    }

    @Override
    public void onFragmentLoaded(MapFragment fragment) {
        mapFragment = fragment;
    }

    @Override
    protected void setupInterceptors(List<Interceptor> interceptorList) {
        interceptorList.add(new AutoInflateLayoutActivityInterceptorImpl(this, this));
        interceptorList.add(new InjectFragmentActivityInterceptorImpl<MapFragment>(this, this));
    }

}
