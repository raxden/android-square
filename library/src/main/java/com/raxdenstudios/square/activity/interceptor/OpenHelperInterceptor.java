package com.raxdenstudios.square.activity.interceptor;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.OpenHelperInterceptorDelegate;

/**
 * Created by agomez on 08/05/2015.
 */
public interface OpenHelperInterceptor<T extends SQLiteOpenHelper> extends Interceptor {

    void onInterceptorCreated(OpenHelperInterceptorDelegate callback);

    T initOpenHelper(Context context, Bundle savedInstanceState);

}
