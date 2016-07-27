package com.raxdenstudios.square.fragment.interceptor;

import com.google.zxing.Result;
import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.fragment.interceptor.delegate.ZXingScannerInterceptorDelegate;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by agomez on 24/12/2015.
 */
public interface ZXingScannerInterceptor extends Interceptor {

    void onInterceptorCreated(ZXingScannerInterceptorDelegate delegate);

    ZXingScannerView onLoadZXingScannerView();

    void handleZXingScannerResult(Result result);

}
