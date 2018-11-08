package com.raxdenstudios.square.interceptor.commons.autoinflateview

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raxdenstudios.square.interceptor.DialogFragmentInterceptor
import com.raxdenstudios.square.utils.ResourceUtils

/**
 * Created by agomez on 02/06/2015.
 */
class AutoInflateViewDialogFragmentInterceptor(
        dialogFragment: DialogFragment,
        callback: AutoInflateViewInterceptorCallback)
    : DialogFragmentInterceptor<AutoInflateViewInterceptorCallback>(dialogFragment, callback),
        AutoInflateViewInterceptor {

    private var mLayoutId: Int = 0

    private val layoutName: String
        get() {
            return dialogFragment.javaClass.simpleName
                    .decapitalize()
                    .split("(?=\\p{Upper})".toRegex())
                    .joinToString(separator = "_")
                    .toLowerCase()
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
