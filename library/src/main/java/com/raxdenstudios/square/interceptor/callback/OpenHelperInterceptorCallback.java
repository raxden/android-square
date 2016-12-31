package com.raxdenstudios.square.interceptor.callback;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.config.OpenHelperInterceptorConfig;

/**
 * Created by agomez on 08/05/2015.
 */
public interface OpenHelperInterceptorCallback<T extends SQLiteOpenHelper>
        extends InterceptorCallback<OpenHelperInterceptorConfig> {

    T onCreateOpenHelper(Context context, Bundle savedInstanceState);

}
