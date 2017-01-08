package com.raxdenstudios.square.lifecycle;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Ángel Gómez
 *
 * Contract that defines the DialogFragment life cycle used by interceptors.
 */
public interface DialogFragmentLifecycle extends FragmentLifecycle {

    Dialog onCreateDialog(Bundle savedInstanceState, Dialog createdDialog);

    void onCancel(DialogInterface dialog);

    void onDismiss(DialogInterface dialog);

}
