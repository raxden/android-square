package com.raxdenstudios.square.activity.interceptor;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.raxdenstudios.square.Interceptor;

/**
 * Created by agomez on 08/05/2015.
 */
public interface OpenHelperInterceptor<T extends SQLiteOpenHelper> extends Interceptor {

    T initOpenHelper(Context context, Bundle bundle);

    interface OpenHelperInterfaceptorCallback {

    }
}
