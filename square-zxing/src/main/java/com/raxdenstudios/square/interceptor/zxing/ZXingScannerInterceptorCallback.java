package com.raxdenstudios.square.interceptor.zxing;

import com.google.zxing.Result;
import com.raxdenstudios.square.interceptor.InterceptorCallback;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by agomez on 24/12/2015.
 */
public interface ZXingScannerInterceptorCallback
        extends InterceptorCallback<ZXingScannerInteractor> {

    ZXingScannerView onLoadZXingScannerView();

    void handleZXingScannerResult(Result result);

}
