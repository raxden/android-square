package com.raxdenstudios.square.fragment;

import android.os.Bundle;

import com.raxdenstudios.square.fragment.module.BundleArgumentsModule;

/**
 * Created by agomez on 20/07/2015.
 */
public abstract class ContentFragment extends AutoInflateFragment implements BundleArgumentsModule {

    private static final String TAG = ContentFragment.class.getSimpleName();

    @Override
    public abstract void onHandleArguments(Bundle savedInstanceState, Bundle arguments);

    @Override
    public abstract void onLoadContent();

}
