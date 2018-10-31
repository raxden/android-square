package com.raxdenstudios.square.utils

/**
 * Created by Ángel Gómez on 09/01/2017.
 */

object LibraryHelper {

    private enum class Status { NOT_AVAILABLE, AVAILABLE }

    private var multiDex: Status? = null

    fun isMultiDexAvailable(): Boolean {
        if (multiDex == null) {
            multiDex = try {
                Class.forName("android.support.multidex.MultiDexApplication")
                Status.AVAILABLE
            } catch (e: ClassNotFoundException) {
                Status.NOT_AVAILABLE
            }
        }
        return Status.AVAILABLE == multiDex
    }

}

