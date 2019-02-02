package com.raxdenstudios.square.interceptor.commons.inflateview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raxdenstudios.square.interceptor.FragmentInterceptor
import com.raxdenstudios.square.utils.ResourceUtils

/**
 * Created by agomez on 02/06/2015.
 */
class InflateViewFragmentInterceptor(
        fragment: Fragment,
        callback: InflateViewInterceptorCallback)
    : FragmentInterceptor<InflateViewInterceptorCallback>(fragment, callback),
        InflateViewInterceptor {

    private var mLayoutId: Int = 0

    private val layoutName: String
        get() {
            return fragment.javaClass.simpleName
                    .decapitalize()
                    .split("(?=\\p{Upper})".toRegex())
                    .joinToString(separator = "_")
                    .toLowerCase()
        }

    override fun onCreateView(inflater: LayoutInflater, inflatedView: View?, container: ViewGroup?, savedInstanceState: Bundle?): View? = when {
        mLayoutId != 0 -> inflater.inflate(mLayoutId, null)
        else -> {
            ResourceUtils.getLayoutId(context, layoutName).let { layoutId ->
                if (layoutId != 0) inflater.inflate(layoutId, null)
                else null
            }
        }
    }

    override fun setLayoutId(layoutId: Int) {
        mLayoutId = layoutId
    }

}
