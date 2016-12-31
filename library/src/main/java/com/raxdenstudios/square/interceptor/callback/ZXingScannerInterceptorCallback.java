package com.raxdenstudios.square.interceptor.callback;

import com.google.zxing.Result;
import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.ZXingScannerInterceptorConfig;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by agomez on 24/12/2015.
 */
public interface ZXingScannerInterceptorCallback
        extends InterceptorCallback<ZXingScannerInterceptorConfig> {

    ZXingScannerView onLoadZXingScannerView();

    void handleZXingScannerResult(Result result);

}
