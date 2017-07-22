package com.raxdenstudios.square.sample.map.presenter;

import android.content.Context;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.square.sample.map.view.MapView;


public class MapPresenter extends Presenter<MapView> implements IMapPresenter {

    public MapPresenter(Context context) {
        super(context);
    }

}
