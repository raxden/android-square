package com.raxdenstudios.square.activity.module.impl;

import android.content.Context;
import android.os.Bundle;

import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.RaterModule;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityImpl;
import com.raxdenstudios.rater.RaterHelper;
import com.raxdenstudios.rater.RaterManager;

/**
 * Created by agomez on 06/05/2015.
 */
public class RaterModuleImpl extends ModuleActivityImpl implements RaterModule.RaterModuleListener {

    private static final String TAG = RaterModule.class.getSimpleName();

    private RaterModule mCallbacks;
    private RaterManager mRaterManager;
    public enum RaterOption {RATE, REMIND_LATER, DONT_SHOW_AGAIN}

    public RaterModuleImpl(ModularActivity activity) {
        if (!(activity instanceof RaterModule)) {
            throw new IllegalStateException("Activity must implement RaterModule.");
        }
        mCallbacks = (RaterModule)activity;
    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);

        mRaterManager = new RaterManager(context);
        if (mCallbacks != null) mCallbacks.onModuleLoaded(this);
    }

    @Override
    public void onModulePostCreate(Context context, Bundle savedInstanceState) {
        super.onModulePostCreate(context, savedInstanceState);

        mRaterManager.showRaterDialogIfNecessary(context, new RaterManager.AppRaterCallbacks() {
            @Override
            public void onDialogClickRate() {
                if (mCallbacks != null) mCallbacks.onRaterModuleClick(RaterOption.RATE);
            }

            @Override
            public void onDialogClickRemindLater() {
                if (mCallbacks != null)
                    mCallbacks.onRaterModuleClick(RaterOption.REMIND_LATER);
            }

            @Override
            public void onDialogClickDontShowAgain() {
                if (mCallbacks != null)
                    mCallbacks.onRaterModuleClick(RaterOption.DONT_SHOW_AGAIN);
            }
        });
    }

    @Override
    public void showRaterDialog(Context context) {
        mRaterManager.showRaterDialog(context, new RaterManager.AppRaterCallbacks() {
            @Override
            public void onDialogClickRate() {
                if (mCallbacks != null) mCallbacks.onRaterModuleClick(RaterOption.RATE);
            }

            @Override
            public void onDialogClickRemindLater() {
                if (mCallbacks != null)
                    mCallbacks.onRaterModuleClick(RaterOption.REMIND_LATER);
            }

            @Override
            public void onDialogClickDontShowAgain() {
                if (mCallbacks != null)
                    mCallbacks.onRaterModuleClick(RaterOption.DONT_SHOW_AGAIN);
            }
        });
    }

    @Override
    public boolean isDontShowAgain(Context context) {
        return RaterHelper.getInstance().isDontShowAgain(context);
    }

    @Override
    public void setDontShowAgain(Context context, boolean dontShowAgain) {
        RaterHelper.getInstance().setDontShowAgain(context, dontShowAgain);
    }

    @Override
    public long getLaunchCounter(Context context) {
        return RaterHelper.getInstance().getLaunchCounter(context);
    }

    @Override
    public void setLaunchCounter(Context context, long launchCount) {
        RaterHelper.getInstance().setLaunchCounter(context, launchCount);
    }

    @Override
    public void setFirstLaunch(Context context, long firstLaunch) {
        RaterHelper.getInstance().setFirstLaunch(context, firstLaunch);
    }

    @Override
    public long getFirstLaunch(Context context) {
        return RaterHelper.getInstance().getFirstLaunch(context);
    }

}
