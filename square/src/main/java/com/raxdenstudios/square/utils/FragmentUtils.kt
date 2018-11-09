package com.raxdenstudios.square.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by Ángel Gómez on 10/06/2017.
 */

object FragmentUtils {

    fun <TFragment : Fragment> loadFragment(fragmentManager: FragmentManager, containerId: Int, fragment: TFragment) {
        fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, fragment.javaClass.simpleName)
                .commit()
    }

    fun <TFragment : Fragment> getFragment(fragmentManager: FragmentManager, containerId: Int): TFragment? =
            fragmentManager.findFragmentById(containerId) as TFragment

}
