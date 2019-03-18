package com.raxdenstudios.square.interceptor.commons.authphone

import com.google.firebase.auth.FirebaseUser
import com.raxdenstudios.square.interceptor.HasInterceptor

interface HasAuthPhoneInterceptor : HasInterceptor {

    fun authPhoneUserRetrieved(firebaseUser: FirebaseUser)

    fun authPhoneCodeRetrieved(code: String)

    fun authPhoneStateChanged(authPhoneState: AuthPhoneInterceptor.AuthPhoneState)

    fun authPhoneError(authPhoneError: AuthPhoneInterceptor.AuthPhoneError)
}
