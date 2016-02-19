package com.raxdenstudios.square.activity.module.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.raxdenstudios.square.R;
import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.CountBackModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public class CountBackModuleImpl extends ModuleActivityImpl implements CountBackModule.CountBackModuleListener {

    private static final String TAG = CountBackModule.class.getSimpleName();

    /* Variables to handle the exit */
    protected Toast exitToast;
    protected int countBackToExit = 1;

    private CountBackConfiguration mConfig;

    private CountBackModule mCallbacks;

    /* Handler */
    protected Handler mHandler = new Handler();

    /* Runnable */
    protected Runnable restartCountBackToExit = new Runnable() {
        public void run() {
            countBackToExit = 1;
        }
    };

    public static class CountBackConfiguration {
        public String exitMessage;
        public CountBackConfiguration(Context context) {}
    }

    public static class DefaultCountBackConfiguration extends CountBackConfiguration {
        public DefaultCountBackConfiguration(Context context) {
            super(context);
            this.exitMessage = context.getString(R.string.app__count_back_exit_message);
        }
    }

    public CountBackModuleImpl(ModularActivity activity) {
        if (!(activity instanceof CountBackModule)) {
            throw new IllegalStateException("Activity must implement CountBackModule.");
        }
        mCallbacks = (CountBackModule)activity;
    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);
        mConfig = new DefaultCountBackConfiguration(context);
        mCallbacks.onModuleLoaded(this);
    }

    @Override
    public boolean onModuleBackPressed(Context context) {
        if (context instanceof ModularActivity) {
            if (((ModularActivity) context).getSupportFragmentManager().getBackStackEntryCount() > 0) {
                return false;
            }
        }

        if (exitToast == null) {
            exitToast = Toast.makeText(context, mConfig.exitMessage, Toast.LENGTH_SHORT);
        }

        if (countBackToExit > 0) {
            countBackToExit--;
            removeCallbacks();
            mHandler.postDelayed(restartCountBackToExit, 2000);
            exitToast.show();
            return true;
        } else {
            exitToast.cancel();
        }
        return false;
    }

    @Override
    public void onModuleDestroy(Context context) {
        super.onModuleDestroy(context);

        removeCallbacks();
        destroyToast();
    }

    private void removeCallbacks() {
        if (mHandler != null) {
            mHandler.removeCallbacks(restartCountBackToExit);
        }
    }

    private void destroyToast() {
        if (exitToast != null) {
            exitToast.cancel();
            exitToast = null;
        }
    }

}
