package com.raxdenstudios.square.interceptor.openhelper;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.raxdenstudios.square.interceptor.InterceptorCallback;

/**
 * Created by agomez on 08/05/2015.
 */
public interface OpenHelperInterceptorCallback<T extends SQLiteOpenHelper> extends InterceptorCallback {

    T onCreateOpenHelper(Context context, Bundle savedInstanceState);

}
