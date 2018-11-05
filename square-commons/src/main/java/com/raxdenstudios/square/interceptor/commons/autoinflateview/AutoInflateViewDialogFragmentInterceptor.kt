package com.raxdenstudios.square.interceptor.commons.autoinflateview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raxdenstudios.square.interceptor.FragmentInterceptor
import com.raxdenstudios.square.utils.ResourceUtils
import com.raxdenstudios.square.utils.StringUtils
import java.util.*

/**
 * Created by agomez on 02/06/2015.
 */
class AutoInflateViewDialogFragmentInterceptor(
        fragment: Fragment,
        callback: AutoInflateViewInterceptorCallback)
    : FragmentInterceptor<AutoInflateViewInterceptorCallback>(fragment, callback),
        AutoInflateViewInterceptor {

    private var mLayoutId: Int = 0

    private val layoutName: String
        get() {
            val className = fragment.javaClass.simpleName.toLowerCase()
            return StringUtils.join(className.split("(?=\\p{Upper})".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray(), "_").toLowerCase(Locale.getDefault())
        }

    override fun onCreateView(inflater: LayoutInflater, inflatedView: View?, container: ViewGroup?, savedInstanceState: Bundle?): View? = when {
        mLayoutId != 0 -> inflater.inflate(mLayoutId, null)
        else -> {
            ResourceUtils.getLayoutId(context, layoutName).let { layoutId ->
                inflater.inflate(layoutId, null).takeIf { layoutId > 0 }
            }
        }
    }

    override fun setLayoutId(layoutId: Int) {
        mLayoutId = layoutId
    }

}
