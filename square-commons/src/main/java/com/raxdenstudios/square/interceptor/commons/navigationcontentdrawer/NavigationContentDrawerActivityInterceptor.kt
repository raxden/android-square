package com.raxdenstudios.square.interceptor.commons.navigationcontentdrawer

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View

import com.raxdenstudios.square.interceptor.commons.R
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.utils.FragmentUtils

/**
 * Created by agomez on 21/05/2015.
 */
class NavigationContentDrawerActivityInterceptor<TFragment : Fragment>
    : ActivityInterceptor<NavigationContentDrawerInterceptorCallback<TFragment>>(), NavigationContentDrawerInterceptor
