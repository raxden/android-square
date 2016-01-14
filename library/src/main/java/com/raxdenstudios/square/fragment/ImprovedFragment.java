package com.raxdenstudios.square.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by agomez on 18/02/2015.
 */
public abstract class ImprovedFragment extends Fragment
        implements OnContentListener, OnProgressListener, OnBackPressedListener, OnNoticeListener {

    private static final String TAG = ImprovedFragment.class.getSimpleName();

    private OnProgressListener onProgressListener;
    private OnNoticeListener onNoticeListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnProgressListener) {
            onProgressListener = (OnProgressListener) activity;
        }
        if (activity instanceof OnNoticeListener) {
            onNoticeListener = (OnNoticeListener) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onLoadContent();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (onProgressListener != null) onProgressListener.onProgressHide();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (onProgressListener != null) onProgressListener = null;
        if (onNoticeListener != null) onNoticeListener = null;
    }

    @Override
    public void onLoadContent() {

    }

    @Override
    public void onRefreshContent() {
        onLoadContent();
    }

    /**
     * Return true to consume back operation.
     * @return
     */
    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onProgressShow(String progressLabel) {
        if (onProgressListener != null) onProgressListener.onProgressShow(progressLabel);
    }

    @Override
    public void onProgressHide() {
        if (onProgressListener != null) onProgressListener.onProgressHide();
    }

    @Override
    public void onNotice(int id, String title, String message) {
        if (onNoticeListener != null) onNoticeListener.onNotice(id, title, message);
    }

}
