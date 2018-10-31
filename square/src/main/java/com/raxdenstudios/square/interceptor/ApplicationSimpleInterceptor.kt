package com.raxdenstudios.square.interceptor

import android.app.Application

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an application interceptor.
 */
abstract class ApplicationSimpleInterceptor(
        application: Application)
    : ApplicationInterceptor<InterceptorCallback>(application)
