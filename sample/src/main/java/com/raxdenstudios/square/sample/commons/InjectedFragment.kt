package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raxdenstudios.square.sample.R
import kotlinx.android.synthetic.main.injected_fragment.*

open class InjectedFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle?) = InjectedFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.injected_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("title")?.also {
            text_view.text = it
        }
        arguments?.getInt("backgroundColor").takeIf { it != 0 }?.also {
            container_view.setBackgroundColor(it)
        }
    }
}