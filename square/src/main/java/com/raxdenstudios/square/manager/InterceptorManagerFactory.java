package com.raxdenstudios.square.manager;

import android.app.Activity;
import android.app.Application;
import android.app.DialogFragment;
import android.app.Fragment;
import android.support.multidex.MultiDexApplication;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.square.utils.LibraryHelper;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

public class InterceptorManagerFactory {

    public static <T> InterceptorManager buildManager(T type) {
        InterceptorManager interceptorManager = null;
        if (LibraryHelper.getInstance().isMultiDexLibraryAvailable() && type instanceof MultiDexApplication) {
            interceptorManager = new ApplicationMultiDexInterceptorManager((MultiDexApplication)type);
        } else if (type instanceof Application) {
            interceptorManager = new ApplicationInterceptorManager((Application) type);
        } else if (type instanceof Activity) {
            interceptorManager = new ActivityInterceptorManager((Activity)type);
        } else if (type instanceof DialogFragment) {
            interceptorManager = new DialogFragmentInterceptorManager((DialogFragment)type);
        } else if (type instanceof Fragment) {
            interceptorManager = new FragmentInterceptorManager((Fragment)type);
        } else if (type instanceof Presenter) {
            interceptorManager = new PresenterInterceptorManager<>((Presenter)type);
        }
        return interceptorManager;
    }



}
