package com.raxdenstudios.square.activity.module;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

/**
 * Created by agomez on 08/05/2015.
 */
public interface OpenHelperModule<T extends SQLiteOpenHelper> {
    T initOpenHelper(Context context, Bundle bundle);
}
