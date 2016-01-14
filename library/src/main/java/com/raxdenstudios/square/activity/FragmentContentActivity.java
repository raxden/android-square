package com.raxdenstudios.square.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.activity.module.BundleExtrasModule;
import com.raxdenstudios.square.activity.module.FragmentContentModule;
import com.raxdenstudios.square.activity.module.LoaderViewModule;
import com.raxdenstudios.square.activity.module.ToolbarModule;
import com.raxdenstudios.square.fragment.OnContentListener;

/**
 * Created by agomez on 02/06/2015.
 */
public abstract class FragmentContentActivity extends AutoInflateActivity
        implements BundleExtrasModule, ToolbarModule, LoaderViewModule, FragmentContentModule {

    private static final String TAG = FragmentContentActivity.class.getSimpleName();

    private FragmentContentModuleListener mFragmentContentModule;
    private LoaderViewModuleListener mLoaderViewModule;
    private ToolbarModuleListener mToolbarModule;

    /* Module callbacks */

    @Override
    public void onHandleExtras(Bundle savedInstanceState, Bundle intentExtras) {

    }

    @Override
    public Toolbar onCreateToolbarView(Bundle savedInstanceState) {
        return (Toolbar)findViewById(R.id.app__toolbar);
    }

    @Override
    public View onCreateLoaderView(Bundle savedInstanceState) {
        return findViewById(R.id.app__progress);
    }

    @Override
    public View onCreateContentFragmentView(Bundle savedInstanceState) {
        return findViewById(R.id.app__content);
    }

    @Override
    public void onModuleLoaded(ToolbarModuleListener module) {
        mToolbarModule = module;
    }

    @Override
    public void onModuleLoaded(LoaderViewModuleListener module) {
        mLoaderViewModule = module;
    }

    @Override
    public void onModuleLoaded(FragmentContentModuleListener module) {
        mFragmentContentModule = module;
    }

    @Override
    public abstract Fragment initContentFragment();

    public void refreshContentFragment() {
        if (mFragmentContentModule.getFragment() != null && mFragmentContentModule.getFragment() instanceof OnContentListener) {
            ((OnContentListener)mFragmentContentModule.getFragment()).onRefreshContent();
        }
    }

    /* Getters && Setters */

    public FragmentContentModuleListener getFragmentContentModule() {
        return mFragmentContentModule;
    }

    public LoaderViewModuleListener getLoaderViewModule() {
        return mLoaderViewModule;
    }

    public ToolbarModuleListener getToolbarModule() {
        return mToolbarModule;
    }

}
