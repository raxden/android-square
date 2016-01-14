package com.raxdenstudios.square.fragment.module.impl;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.raxdenstudios.square.fragment.ModularFragment;
import com.raxdenstudios.square.fragment.module.ZXingScannerModule;
import com.raxdenstudios.square.fragment.module.manager.ModuleFragmentImpl;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by agomez on 24/12/2015.
 */
public class ZXingScannerModuleImpl extends ModuleFragmentImpl implements ZXingScannerModule.ZXingScannerModuleListener, ZXingScannerView.ResultHandler {

    private static final String TAG = AutoInflateViewModuleImpl.class.getSimpleName();

    private ZXingScannerModule mCallbacks;
    private ZXingScannerView mScannerView;

    private boolean mFlash;
    private boolean mAutoFocus;
    private ArrayList<Integer> mSelectedIndices;
    private int mCameraId = -1;

    public ZXingScannerModuleImpl(ModularFragment fragment) {
        if (!(fragment instanceof ZXingScannerModule)) {
            throw new IllegalStateException("Fragment must implement ZXingScannerModule.");
        }
        mCallbacks = (ZXingScannerModule)fragment;
    }

    @Override
    public void onModuleViewCreated(Context context, View view, Bundle savedInstanceState) {
        super.onModuleViewCreated(context, view, savedInstanceState);

        mScannerView = mCallbacks != null ? mCallbacks.onLoadZXingScannerView() : null;
        if (mScannerView != null) {
            mFlash = false;
            mAutoFocus = true;
            mSelectedIndices = null;
            mCameraId = -1;
            setupFormats();
        }
        if (mCallbacks != null) {
            mCallbacks.onModuleLoaded(this);
        }
    }

    @Override
    public void onModuleResume(Context context) {
        super.onModuleResume(context);

        if (mScannerView != null) {
            mScannerView.setResultHandler(this);
            mScannerView.startCamera(mCameraId);
            mScannerView.setFlash(mFlash);
            mScannerView.setAutoFocus(mAutoFocus);
        }
    }

    @Override
    public void onModulePause(Context context) {
        super.onModulePause(context);

        if (mScannerView != null) {
            mScannerView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result result) {
        if (mCallbacks != null) {
            mCallbacks.handleZXingScannerResult(result);
        }
    }

    private void setupFormats() {
        List<BarcodeFormat> formats = new ArrayList<>();
        if (mSelectedIndices == null || mSelectedIndices.isEmpty()) {
            mSelectedIndices = new ArrayList<>();
            for(int i = 0; i < ZXingScannerView.ALL_FORMATS.size(); i++) {
                mSelectedIndices.add(i);
            }
        }

        for (int index : mSelectedIndices) {
            formats.add(ZXingScannerView.ALL_FORMATS.get(index));
        }

        if (mScannerView != null) {
            mScannerView.setFormats(formats);
        }
    }


}
