package com.raxdenstudios.square.lifecycle;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.FragmentActivity;

/**
 * Created by Ángel Gómez
 *
 * Contract that defines the Fragment life cycle used by interceptors.
 */
public interface FragmentLifecycle {

    void onSaveInstanceState(Bundle outState);

    void onViewStateRestored(Bundle savedInstanceState);

    void onAttach(FragmentActivity activity);

    void onAttach(Context context);

    void onCreate(Bundle savedInstanceState);

    View onCreateView(LayoutInflater inflater, View inflatedView, ViewGroup container, Bundle savedInstanceState);

    void onViewCreated(View view, Bundle savedInstanceState);

    void onActivityCreated(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onDestroyView();

    void onDetach();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onConfigurationChanged(Configuration configuration);

}
