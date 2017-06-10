package com.raxdenstudios.square.utils;

import android.content.Context;

/**
 * Created by Ángel Gómez on 09/06/2017.
 */

public class ResourceUtils {

    public static int getLayoutId(Context context, String name) {
        int resID = 0;
        if (name != null && name.length() > 0) {
            resID = context.getResources().getIdentifier(name.replaceAll("R.layout.", ""), "layout", context.getPackageName());
        }
        return resID;
    }

}
