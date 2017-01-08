package com.raxdenstudios.square.interceptor.callback;

import com.google.zxing.Result;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.ZXingScannerInterceptorInteractor;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by agomez on 24/12/2015.
 */
public interface ZXingScannerInterceptorCallback
        extends InterceptorCallback<ZXingScannerInterceptorInteractor> {

    ZXingScannerView onLoadZXingScannerView();

    void handleZXingScannerResult(Result result);

}
