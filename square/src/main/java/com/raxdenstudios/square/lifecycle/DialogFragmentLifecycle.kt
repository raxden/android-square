package com.raxdenstudios.square.lifecycle

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle

/**
 * Created by Ángel Gómez
 *
 * Contract that defines the DialogFragment life cycle used by interceptors.
 */
interface DialogFragmentLifecycle : FragmentLifecycle {

    fun onCreateDialog(savedInstanceState: Bundle?, createdDialog: Dialog): Dialog

    fun onCancel(dialog: DialogInterface?)

    fun onDismiss(dialog: DialogInterface?)

}
