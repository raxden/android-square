package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.HasToolbarInterceptor
import com.raxdenstudios.square.sample.R
import kotlinx.android.synthetic.main.circular_reveal_animation_activity.*

class CircularRevealAnimationActivity : AppCompatActivity(),
        HasAutoInflateLayoutInterceptor,
        HasToolbarInterceptor {

    private var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor? = null

    var mContentView: View? = null
    var mToolbarView: Toolbar? = null

    var mMainFragment: Fragment? = null
    var mDetailFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            mMainFragment = MainFragment.newInstance(intent.extras).also {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container_view, it)
                        .commit()
            }
        } else {
            mMainFragment = supportFragmentManager.findFragmentById(R.id.container_view)
        }
    }

    // ======== HasInflateLayoutInterceptor ========================================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
        floating_action_button.setOnClickListener {
            mDetailFragment = DetailFragment.newInstance(intent.extras).also {
                supportFragmentManager
                        .beginTransaction()
                        .addToBackStack(DetailFragment.javaClass.simpleName)
                        .replace(R.id.container_view, it)
                        .commit()
            }
        }
    }

    override fun onInterceptorCreated(interceptor: Interceptor) {
        mAutoInflateLayoutInterceptor = interceptor as? AutoInflateLayoutInterceptor
    }

    // ======== HasToolbarInterceptor ==============================================================

    override fun onCreateToolbarView(savedInstanceState: Bundle?): Toolbar = toolbar_view

    override fun onToolbarViewCreated(toolbar: Toolbar) {
        mToolbarView = toolbar

        mToolbarView?.setNavigationOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            }
        }

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                mToolbarView?.setNavigationIcon(R.drawable.ic_close_black_24dp)
                floating_action_button.hide()
            } else {
                mToolbarView?.navigationIcon = null
                floating_action_button.show()
            }
        }
    }

    // =============================================================================================

    class MainFragment : Fragment() {

        companion object {
            fun newInstance(bundle: Bundle?) = MainFragment().apply {
                arguments = bundle ?: Bundle()
            }
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.main_fragment, container, false)
        }
    }

    class DetailFragment : Fragment() {

        companion object {
            fun newInstance(bundle: Bundle?) = DetailFragment().apply {
                arguments = bundle ?: Bundle()
            }
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.detail_fragment, container, false)
        }
    }

}