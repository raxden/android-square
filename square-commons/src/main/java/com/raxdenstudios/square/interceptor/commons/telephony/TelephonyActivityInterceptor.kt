package com.raxdenstudios.square.interceptor.commons.telephony

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager

import com.raxdenstudios.square.interceptor.ActivityInterceptor

/**
 * Created by Raxden on 16/12/2016.
 */
class TelephonyActivityInterceptor(
        callback: HasTelephonyInterceptor
) : ActivityInterceptor<TelephonyInterceptor, HasTelephonyInterceptor>(callback),
        TelephonyInterceptor {

    private var mTelephonyManager: TelephonyManager? = null

    private val mPhoneStateListener = object : PhoneStateListener() {

        override fun onCallStateChanged(state: Int, incomingNumber: String) {
            mCallback.onCallStateChanged(state, incomingNumber)
            super.onCallStateChanged(state, incomingNumber)
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        activity?.apply {
            mTelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            mTelephonyManager?.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE)
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        mTelephonyManager?.apply {
            listen(mPhoneStateListener, PhoneStateListener.LISTEN_NONE)
        }
        super.onActivityDestroyed(activity)
    }
}
