package com.raxdenstudios.square.fragment.interceptor;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by agomez on 24/12/2015.
 */
public interface ZXingScannerInterceptor {

    void onInterceptorLoaded(ZXingScannerInterceptorListener interceptor);
    ZXingScannerView onLoadZXingScannerView();
    void handleZXingScannerResult(Result result);

    interface ZXingScannerInterceptorListener {

    }

}
