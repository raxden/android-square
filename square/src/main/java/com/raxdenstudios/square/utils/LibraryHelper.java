package com.raxdenstudios.square.utils;

/**
 * Created by Ángel Gómez on 09/01/2017.
 */

public class LibraryHelper {

    private static LibraryHelper instance = null;
    private enum Status {NOT_AVAILABLE, AVAILABLE}

    private Status multiDexLibrary;
    private Status mvpLibrary;

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
                Class.forName("androidx.multidex.MultiDexApplication");
                multiDexLibrary = Status.AVAILABLE;
            } catch (ClassNotFoundException e) {
                multiDexLibrary = Status.NOT_AVAILABLE;
            }
        }
        return multiDexLibrary == Status.AVAILABLE;
    }

}
