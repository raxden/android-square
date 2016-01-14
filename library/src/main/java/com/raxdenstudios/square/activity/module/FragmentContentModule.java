package com.raxdenstudios.square.activity.module;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * Created by agomez on 02/06/2015.
 */
public interface FragmentContentModule {

    View onCreateContentFragmentView(Bundle savedInstanceState);
    Fragment initContentFragment();
    void onModuleLoaded(FragmentContentModuleListener module);

    interface FragmentContentModuleListener {

        void replaceFragment(Fragment fragment);
        void replaceFragment(Fragment fragment, boolean addToBackStack);
        void replaceFragment(Fragment fragment, FragmentTransaction fragmentTransaction);

        void removeFragment(Fragment fragment);

        View getFragmentView();
        Fragment getFragment();

    }
}
