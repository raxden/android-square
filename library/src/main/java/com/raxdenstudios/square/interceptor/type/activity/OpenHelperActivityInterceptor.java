package com.raxdenstudios.square.interceptor.type.activity;

import android.database.sqlite.SQLiteOpenHelper;

import com.raxdenstudios.square.interceptor.callback.OpenHelperInterceptorCallback;

/**
 * Created by agomez on 08/05/2015.
 */
public interface OpenHelperActivityInterceptor<T extends SQLiteOpenHelper>
        extends OpenHelperInterceptorCallback<T> {

}
