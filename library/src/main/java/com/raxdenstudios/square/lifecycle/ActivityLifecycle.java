package com.raxdenstudios.square.lifecycle;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */
public interface ActivityLifecycle {

    void onSaveInstanceState(Bundle outState);

    void attachBaseContext(Context newBase);

    void onCreate(Bundle savedInstanceState);

    void onPostCreate(Bundle savedInstanceState);

    void onCreateOptionsMenu(Menu menu);

    void onPrepareOptionsMenu(Menu menu);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    boolean onBackPressed();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onConfigurationChanged(Configuration configuration);

}
