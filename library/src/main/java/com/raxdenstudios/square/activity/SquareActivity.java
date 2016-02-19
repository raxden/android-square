package com.raxdenstudios.square.activity;

import android.support.v7.app.AppCompatActivity;

import com.raxdenstudios.square.fragment.OnNoticeListener;
import com.raxdenstudios.square.fragment.OnProgressListener;
import com.raxdenstudios.square.fragment.dialog.WildcardDialogFragment;

/**
 * Created by agomez on 18/02/2015.
 */
public class SquareActivity extends AppCompatActivity
        implements OnProgressListener, OnNoticeListener {

    private static final String TAG = SquareActivity.class.getSimpleName();

    /* Callbacks */

    @Override
    public void onProgressShow(String progressLabel) {

    }

    @Override
    public void onProgressHide() {

    }

    @Override
    public void onNotice(int id, String title, String message) {
        WildcardDialogFragment.show(getSupportFragmentManager(), title, message, new WildcardDialogFragment.WildcardDialogFragmentButton(getString(android.R.string.ok), null));
    }

}
