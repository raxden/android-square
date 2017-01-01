package com.raxdenstudios.square.lifecycle;

import android.os.Bundle;

import com.raxdenstudios.mvp.view.IView;

/**
 * Created by Ángel Gómez
 *
 * Contract that defines the Presenter life cycle used by interceptors.
 */
public interface PresenterLifecycle<TView extends IView> {

    void onTakeView(TView mView);

    void onCreate(Bundle savedInstanceState);

    void onResume();

    void onPause();

    void onViewLoaded();

    void onSave(Bundle outState);

    void onDestroy();

    void onDropView();

}
