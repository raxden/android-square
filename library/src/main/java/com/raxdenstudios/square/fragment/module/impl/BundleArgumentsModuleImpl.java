package com.raxdenstudios.square.fragment.module.impl;

import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.square.fragment.ModularFragment;
import com.raxdenstudios.square.fragment.module.BundleArgumentsModule;
import com.raxdenstudios.square.fragment.module.manager.ModuleFragmentImpl;

/**
 * Created by agomez on 22/05/2015.
 */
public class BundleArgumentsModuleImpl extends ModuleFragmentImpl {

    private static final String TAG = BundleArgumentsModuleImpl.class.getSimpleName();

    private BundleArgumentsModule mCallbacks;

    public BundleArgumentsModuleImpl(ModularFragment fragment) {
        if (!(fragment instanceof BundleArgumentsModule)) {
            throw new IllegalStateException("Fragment must implement BundleArgumentsModule.");
        }
        mCallbacks = (BundleArgumentsModule)fragment;
    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);

        Bundle arguments = null;
        if (mCallbacks instanceof ModularFragment) {
            arguments = ((ModularFragment)mCallbacks).getArguments();
        }

        if (mCallbacks != null) mCallbacks.onHandleArguments(savedInstanceState, arguments);
    }

}
