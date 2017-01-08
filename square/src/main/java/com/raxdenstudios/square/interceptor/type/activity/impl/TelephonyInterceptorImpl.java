package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.raxdenstudios.square.interceptor.callback.TelephonyInterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.TelephonyInterceptorInteractor;
import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;

/**
 * Created by Raxden on 16/12/2016.
 */

public class TelephonyInterceptorImpl
        extends ActivityInterceptor<TelephonyInterceptorInteractor, TelephonyInterceptorCallback>
        implements TelephonyInterceptorInteractor {

    private TelephonyManager mTelephonyManager;

    public TelephonyInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTelephonyManager = (TelephonyManager) mActivity.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyManager != null) {
            mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mTelephonyManager != null) {
            mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_NONE);
        }
    }

    private PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            mCallback.onCallStateChanged(state, incomingNumber);
            super.onCallStateChanged(state, incomingNumber);
        }
    };

}
