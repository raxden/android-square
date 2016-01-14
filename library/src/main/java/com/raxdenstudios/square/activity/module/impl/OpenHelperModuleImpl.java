package com.raxdenstudios.square.activity.module.impl;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.OpenHelperModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;

/**
 * Created by agomez on 08/05/2015.
 */
public class OpenHelperModuleImpl extends ModuleActivityImpl {

    private static final String TAG = OpenHelperModuleImpl.class.getSimpleName();

    private OpenHelperModule mCallbacks;
    private SQLiteOpenHelper mOpenHelper;

    public OpenHelperModuleImpl(ModularActivity activity) {
        if (!(activity instanceof OpenHelperModule)) {
            throw new IllegalStateException("Activity must implement OpenHelperModule.");
        }
        mCallbacks = (OpenHelperModule)activity;
    }

    @Override
    public void onModuleCreate(Context context, Bundle bundle) {
        super.onModuleCreate(context, bundle);

        if (mCallbacks != null) {
            mOpenHelper = mCallbacks.initOpenHelper(context, bundle);
        }
    }

    @Override
    public void onModuleDestroy(Context context) {
        super.onModuleDestroy(context);

        if (mOpenHelper != null) {
            mOpenHelper.close();
            mOpenHelper = null;
        }
    }
}
