package com.raxdenstudios.square.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by Ángel Gómez on 10/06/2017.
 */

object FragmentUtils {

    fun loadFragment(fragmentManager: FragmentManager, containerId: Int, fragment: Fragment) {
        fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, fragment.javaClass.simpleName)
                .commit()
    }

    fun getFragment(fragmentManager: FragmentManager, containerId: Int): Fragment? = fragmentManager.findFragmentById(containerId)

}
