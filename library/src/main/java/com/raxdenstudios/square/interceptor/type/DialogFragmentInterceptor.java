package com.raxdenstudios.square.interceptor.type;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.interceptor.BaseInterceptor;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorConfig;
import com.raxdenstudios.square.lifecycle.DialogFragmentLifecycle;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

public abstract class DialogFragmentInterceptor<TConfig extends InterceptorConfig, TCallback extends InterceptorCallback<TConfig>>
        extends BaseInterceptor<TConfig, TCallback>
        implements DialogFragmentLifecycle {

    protected DialogFragment mDialogFragment;

    public DialogFragmentInterceptor(DialogFragment dialogFragment) {
        super(dialogFragment);
        mDialogFragment = dialogFragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onAttach(Activity activity) {

    }

    @Override
    public void onAttach(Context context) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, View inflatedView, ViewGroup container, Bundle savedInstanceState) {
        return inflatedView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState, Dialog createdDialog) {
        return createdDialog;
    }

    @Override
    public void onCancel(DialogInterface dialog) {

    }

    @Override
    public void onDismiss(DialogInterface dialog) {

    }

}
