package com.raxdenstudios.square.interceptor.telephony;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by Raxden on 16/12/2016.
 */

public class TelephonyActivityInterceptorImpl extends ActivityInterceptor<TelephonyInterceptorCallback> implements TelephonyInterceptor {

    private TelephonyManager mTelephonyManager;

    public TelephonyActivityInterceptorImpl(@NonNull Activity activity) {
        super(activity);
    }

    public TelephonyActivityInterceptorImpl(@NonNull Activity activity, @NonNull TelephonyInterceptorCallback callback) {
        super(activity, callback);
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
