package com.raxdenstudios.square.activity.module.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.BundleExtrasModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;

/**
 * Created by agomez on 22/05/2015.
 */
public class BundleExtrasModuleImpl extends ModuleActivityImpl {

    private static final String TAG = BundleExtrasModuleImpl.class.getSimpleName();

    private BundleExtrasModule mCallbacks;

    public BundleExtrasModuleImpl(ModularActivity activity) {
        if (!(activity instanceof BundleExtrasModule)) {
            throw new IllegalStateException("Activity must implement BundleExtrasModule.");
        }
        mCallbacks = (BundleExtrasModule)activity;
    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);

        Bundle bundleExtras = null;
        if (context instanceof Activity) {
            bundleExtras = ((Activity)context).getIntent().getExtras();
        }

        if (mCallbacks != null) mCallbacks.onHandleExtras(savedInstanceState, bundleExtras);
    }

}
