package com.raxdenstudios.square.lifecycle;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Ángel Gómez on 31/12/2016.
 */

public interface DialogFragmentLifecycle extends FragmentLifecycle {

    Dialog onCreateDialog(Bundle savedInstanceState, Dialog createdDialog);

    void onCancel(DialogInterface dialog);

    void onDismiss(DialogInterface dialog);

}
