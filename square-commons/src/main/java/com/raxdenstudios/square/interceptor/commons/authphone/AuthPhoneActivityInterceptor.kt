package com.raxdenstudios.square.interceptor.commons.authphone

import android.app.Activity
import android.os.Bundle
import android.util.Log

import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.raxdenstudios.square.Utils
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import java.util.concurrent.TimeUnit

class AuthPhoneActivityInterceptor(
        callback: HasAuthPhoneInterceptor
) : ActivityInterceptor<AuthPhoneInterceptor, HasAuthPhoneInterceptor>(callback),
        AuthPhoneInterceptor {

    private val TAG = AuthPhoneActivityInterceptor::class.java.simpleName

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mAuthPhoneState: AuthPhoneInterceptor.AuthPhoneState
    private lateinit var mVerificationStateChangedCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private val mDebug: Boolean = false
    private var mActivity: Activity? = null
    private var mPhoneNumber: String? = null
    private var mVerificationId: String? = null
    private var mVerificationInProgress = false
    private var mResendToken: PhoneAuthProvider.ForceResendingToken? = null

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
        super.onActivitySaveInstanceState(activity, outState)

        outState?.putBoolean("verificationInProgress", mVerificationInProgress)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        mActivity = activity
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onActivityStarted(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityStarted(activity, savedInstanceState)

        mAuth.currentUser?.also {
            authPhoneUserRetrieved(it)
            authPhoneStateChanged(AuthPhoneInterceptor.AuthPhoneState.STATE_SIGNIN_SUCCESS)
        } ?: authPhoneStateChanged(AuthPhoneInterceptor.AuthPhoneState.STATE_INITIALIZED)

        initializePhoneAuthCallbacks()

        if (savedInstanceState?.getBoolean("verificationInProgress", false) == true) {
            performStartPhoneVerification()
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        mActivity = null
        super.onActivityDestroyed(activity)
    }

    override fun startPhoneVerification(phone: String) {
        mPhoneNumber = phone
        if (Utils.isEmulator()) {
            authPhoneStateChanged(AuthPhoneInterceptor.AuthPhoneState.STATE_SIGNIN_SUCCESS)
        } else {
            performStartPhoneVerification()
        }
    }

    override fun resendVerificationCode() {
        mActivity?.also {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    mPhoneNumber ?: "",                     // Phone number to verify
                    60,                                     // Timeout duration
                    TimeUnit.SECONDS,                       // Unit of timeout
                    it,                                     // Activity (for callback binding)
                    mVerificationStateChangedCallbacks,     // OnVerificationStateChangedCallbacks
                    mResendToken)                           // ForceResendingToken from callbacks
        }
    }

    override fun startCodeVerification(code: String) {
        if (mDebug) {
            authPhoneStateChanged(AuthPhoneInterceptor.AuthPhoneState.STATE_SIGNIN_SUCCESS)
            return
        }
        signInWithPhoneAuthCredential(PhoneAuthProvider.getCredential(mVerificationId ?: "", code))
    }

    override fun signOut() {
        mAuth.signOut()
        authPhoneStateChanged(AuthPhoneInterceptor.AuthPhoneState.STATE_INITIALIZED)
    }

    private fun performStartPhoneVerification() {
        mActivity?.also {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    mPhoneNumber ?: "",                     // Phone number to verify
                    60,                                     // Timeout duration
                    TimeUnit.SECONDS,                       // Unit of timeout
                    it,                                     // Activity (for callback binding)
                    mVerificationStateChangedCallbacks)     // OnVerificationStateChangedCallbacks
            mVerificationInProgress = true
        }
    }

    private fun initializePhoneAuthCallbacks() {
        mVerificationStateChangedCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential?) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to save or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                credential?.also {
                    Log.d(TAG, "onVerificationCompleted: $it")
                    mVerificationInProgress = false
                    // Update the UI and attempt sign in with the phone credential
                    authPhoneStateChanged(AuthPhoneInterceptor.AuthPhoneState.STATE_VERIFY_SUCCESS)
                    authPhoneCredentialRetrieved(credential)
                    signInWithPhoneAuthCredential(credential)
                }
            }

            override fun onVerificationFailed(exception: FirebaseException?) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.e(TAG, exception?.message, exception)
                mVerificationInProgress = false
                when (exception) {
                    is FirebaseAuthInvalidCredentialsException -> authPhoneError(AuthPhoneInterceptor.AuthPhoneError.INVALID_PHONE_NUMBER)
                    is FirebaseTooManyRequestsException -> authPhoneError(AuthPhoneInterceptor.AuthPhoneError.QUOTA_EXCEEDED)
                }
                authPhoneStateChanged(AuthPhoneInterceptor.AuthPhoneState.STATE_VERIFY_FAILED)
            }

            override fun onCodeSent(verificationId: String?, token: PhoneAuthProvider.ForceResendingToken?) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent: $verificationId")
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId
                mResendToken = token
                authPhoneStateChanged(AuthPhoneInterceptor.AuthPhoneState.STATE_CODE_SENT)
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mActivity?.also { activity ->
            mAuth.signInWithCredential(credential).addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's additionalData
                    Log.d(TAG, "signInWithCredential:success")
                    task.result?.user?.also {
                        authPhoneUserRetrieved(it)
                        authPhoneStateChanged(AuthPhoneInterceptor.AuthPhoneState.STATE_SIGNIN_SUCCESS)
                    }
                            ?: authPhoneStateChanged(AuthPhoneInterceptor.AuthPhoneState.STATE_INITIALIZED)
                } else {
                    // Sign in failed, display a message and update the UI
                    task.exception?.also {
                        Log.e(TAG, it.message, it)
                        when (task.exception) {
                            is FirebaseAuthInvalidCredentialsException -> authPhoneError(AuthPhoneInterceptor.AuthPhoneError.INVALID_CODE)
                            else -> authPhoneStateChanged(AuthPhoneInterceptor.AuthPhoneState.STATE_SIGNIN_FAILED)
                        }
                    }
                }
            }
        }
    }

    private fun authPhoneUserRetrieved(user: FirebaseUser) {
        mCallback.authPhoneUserRetrieved(user)
    }

    private fun authPhoneError(authPhoneError: AuthPhoneInterceptor.AuthPhoneError) {
        mCallback.authPhoneError(authPhoneError)
    }

    private fun authPhoneStateChanged(state: AuthPhoneInterceptor.AuthPhoneState) {
        mAuthPhoneState = state
        mCallback.authPhoneStateChanged(state)
    }

    private fun authPhoneCredentialRetrieved(credential: PhoneAuthCredential) {
        credential.smsCode?.also {
            mCallback.authPhoneCodeRetrieved(it)
        } ?: authPhoneError(AuthPhoneInterceptor.AuthPhoneError.INSTANT_VALIDATION)
    }
}
