package com.raxdenstudios.square.fragment.module;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by agomez on 24/12/2015.
 */
public interface ZXingScannerModule {

    void onModuleLoaded(ZXingScannerModuleListener module);
    ZXingScannerView onLoadZXingScannerView();
    void handleZXingScannerResult(Result result);

    interface ZXingScannerModuleListener {

    }

}
