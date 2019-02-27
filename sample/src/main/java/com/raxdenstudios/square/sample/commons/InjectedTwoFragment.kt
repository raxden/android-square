package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raxdenstudios.square.sample.R
import kotlinx.android.synthetic.main.injected_fragment.*

open class InjectedTwoFragment: Fragment() {

    companion object {
        fun newInstance(bundle: Bundle?) = InjectedTwoFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.injected_two_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_view.text = arguments?.getString("title")
    }
}