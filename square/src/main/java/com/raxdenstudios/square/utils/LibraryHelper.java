package com.raxdenstudios.square.utils;

/**
 * Created by Ángel Gómez on 09/01/2017.
 */

public class LibraryHelper {

    private static LibraryHelper instance = null;
    private enum Status {NO_OK, OK}

    private Status multiDexLibrary;

    private LibraryHelper() {

    }

    private static LibraryHelper createInstance() {
        return new LibraryHelper();
    }

    public static LibraryHelper getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    public boolean isMultiDexLibraryAvailable() {
        if (multiDexLibrary == null) {
            try {
                Class.forName("android.support.multidex.MultiDexApplication");
                multiDexLibrary = Status.OK;
            } catch (ClassNotFoundException e) {
                multiDexLibrary = Status.NO_OK;
            }
        }
        return multiDexLibrary == Status.OK;
    }


}
