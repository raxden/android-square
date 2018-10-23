package com.raxdenstudios.square.manager;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.interceptor.DialogFragmentInterceptor;
import com.raxdenstudios.square.lifecycle.DialogFragmentLifecycle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.DialogFragment;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

public class DialogFragmentInterceptorManager extends InterceptorManager<DialogFragment, DialogFragmentInterceptor>
        implements DialogFragmentLifecycle {

    public DialogFragmentInterceptorManager(DialogFragment fragment) {
        super(fragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onViewStateRestored(savedInstanceState);
        }
    }

    @Override
    public void onAttach(FragmentActivity activity) {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onAttach(activity);
        }
    }

    @Override
    public void onAttach(Context context) {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onAttach(context);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onCreate(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, View inflatedView, ViewGroup container, Bundle savedInstanceState) {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            inflatedView = interceptor.onCreateView(inflater, inflatedView, container, savedInstanceState);
        }
        return inflatedView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onViewCreated(view, savedInstanceState);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onActivityCreated(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onStart();
        }
    }

    @Override
    public void onResume() {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onResume();
        }
    }

    @Override
    public void onPause() {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onPause();
        }
    }

    @Override
    public void onStop() {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onStop();
        }
    }

    @Override
    public void onDestroy() {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onDestroy();
        }
    }

    @Override
    public void onDestroyView() {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onDestroyView();
        }
    }

    @Override
    public void onDetach() {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onDetach();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onConfigurationChanged(configuration);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState, Dialog dialog) {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            dialog = interceptor.onCreateDialog(savedInstanceState, dialog);
        }
        return dialog;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onCancel(dialog);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        for (DialogFragmentInterceptor interceptor : interceptors) {
            interceptor.onDismiss(dialog);
        }
    }

}
