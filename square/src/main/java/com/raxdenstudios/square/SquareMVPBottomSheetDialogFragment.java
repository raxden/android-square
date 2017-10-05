package com.raxdenstudios.square;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.mvp.MVPBottomSheetDialogFragment;
import com.raxdenstudios.mvp.presenter.IPresenter;
import com.raxdenstudios.square.interceptor.DialogFragmentInterceptor;
import com.raxdenstudios.square.interceptor.Interceptor;
import com.raxdenstudios.square.manager.DialogFragmentInterceptorManager;
import com.raxdenstudios.square.manager.InterceptorManagerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ángel Gómez
 *
 * SquareMVPDialogFragment is an abstract class that adds interceptor functionality to the
 * DialogFragment. Unlike SquareDialogFragment this activity follows the MVP pattern, therefore has
 * a presenter attached.
 */
public abstract class SquareMVPBottomSheetDialogFragment<TPresenter extends IPresenter>
        extends MVPBottomSheetDialogFragment<TPresenter> {

    private DialogFragmentInterceptorManager mInterceptorManager;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getInterceptorManager().onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        getInterceptorManager().onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getInterceptorManager().onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInterceptorManager().onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = super.onCreateView(inflater, container, savedInstanceState);
        return getInterceptorManager().onCreateView(inflater, parentView, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getInterceptorManager().onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getInterceptorManager().onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getInterceptorManager().onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        getInterceptorManager().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getInterceptorManager().onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        getInterceptorManager().onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getInterceptorManager().onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getInterceptorManager().onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getInterceptorManager().onDetach();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        return getInterceptorManager().onCreateDialog(savedInstanceState, dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        getInterceptorManager().onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getInterceptorManager().onDismiss(dialog);
    }

    /* Callbacks */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getInterceptorManager().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getInterceptorManager().onConfigurationChanged(newConfig);
    }

    /* Support methods */

    protected abstract void setupInterceptors(List<Interceptor> interceptorList);

    private DialogFragmentInterceptorManager getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = (DialogFragmentInterceptorManager) InterceptorManagerFactory.buildManager(this);
            List<Interceptor> interceptorList = new ArrayList<>();
            setupInterceptors(interceptorList);
            for (Interceptor interceptor : interceptorList) {
                if (interceptor instanceof DialogFragmentInterceptor) {
                    mInterceptorManager.addInterceptor((DialogFragmentInterceptor) interceptor);
                }
            }
        }
        return mInterceptorManager;
    }

}
