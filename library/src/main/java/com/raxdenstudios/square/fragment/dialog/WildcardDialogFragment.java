package com.raxdenstudios.square.fragment.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class WildcardDialogFragment extends DialogFragmentImproved {
	
	private static final String TAG = WildcardDialogFragment.class.getSimpleName();

    public static interface OnWildcardDialogFragmentButtonClickListener {
        public void onButtonClick(WildcardDialogFragment dialogFragment);
    }

    public static class WildcardDialogFragmentButton {

        public String title;
        public OnWildcardDialogFragmentButtonClickListener mListener;

        public WildcardDialogFragmentButton(String title, OnWildcardDialogFragmentButtonClickListener listener) {
            this.title = title;
            this.mListener = listener;
        }
    }

    protected String title;
    protected String content;
    protected WildcardDialogFragmentButton positiveButton;
    protected WildcardDialogFragmentButton negativeButton;
    protected WildcardDialogFragmentButton genericButton;

    public static DialogFragment show(FragmentManager fragmentManager, String title, String message) {
        return show(fragmentManager, title, message, null, null, null);
    }

    public static DialogFragment show(FragmentManager fragmentManager, String title, String message, WildcardDialogFragmentButton genericButton) {
        return show(fragmentManager, title, message, null, null, genericButton);
    }

    public static DialogFragment show(FragmentManager fragmentManager, String title, String message, WildcardDialogFragmentButton positiveButton, WildcardDialogFragmentButton negativeButton) {
        return show(fragmentManager, title, message, positiveButton, negativeButton, null);
    }

    public static DialogFragment show(FragmentManager fragmentManager, String title, String message, WildcardDialogFragmentButton positiveButton, WildcardDialogFragmentButton negativeButton, WildcardDialogFragmentButton genericButton) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag(WildcardDialogFragment.class.getSimpleName());
        if (prev != null && prev.isVisible()) {
            return null;
        }
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        WildcardDialogFragment wildcardDialog = null;
        try  {
            wildcardDialog = WildcardDialogFragment.newInstance(title, message);
            wildcardDialog.setPositiveButton(positiveButton);
            wildcardDialog.setNegativeButton(negativeButton);
            wildcardDialog.setGenericButton(genericButton);
            wildcardDialog.show(ft, WildcardDialogFragment.class.getSimpleName());
        } catch (IllegalStateException e) {
            Log.d(TAG, e.getMessage(), e);
        }
        return wildcardDialog;
    }

    public static WildcardDialogFragment newInstance(String title, String message) {
        WildcardDialogFragment f = new WildcardDialogFragment();

        // Supply title input as an argument.
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("content", message);
        f.setArguments(args);

        return f;
    }

    public static WildcardDialogFragment newInstance(int layout, String title, String content) {
        WildcardDialogFragment f = new WildcardDialogFragment();

        // Supply title input as an argument.
        Bundle args = new Bundle();
        args.putInt("layout", layout);
        args.putString("title", title);
        args.putString("content", content);
        f.setArguments(args);

        return f;
    }

    public void onHandleArguments(Bundle arguments) {
        if (arguments != null) {
            layout = getArguments().getInt("layout");
            title = getArguments().getString("title");
            content = getArguments().getString("content");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (view.findViewById(android.R.id.title) != null && view.findViewById(android.R.id.title) instanceof TextView) {
            ((TextView) view.findViewById(android.R.id.title)).setText(title);
        }
        if (view.findViewById(android.R.id.content) != null && view.findViewById(android.R.id.content) instanceof TextView) {
            ((TextView) view.findViewById(android.R.id.content)).setText(content);
        }

        if (positiveButton != null || negativeButton != null || genericButton != null) {
            if (view.findViewById(android.R.id.tabcontent) != null) {
                view.findViewById(android.R.id.tabcontent).setVisibility(View.VISIBLE);
            }
        }

        if (positiveButton != null && view.findViewById(android.R.id.button1) != null && view.findViewById(android.R.id.button1) instanceof TextView) {
            view.findViewById(android.R.id.button1).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(android.R.id.button1)).setText(positiveButton.title);
            ((TextView)view.findViewById(android.R.id.button1)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPositiveButtonClick();
                }
            });
        }

        if (genericButton != null && view.findViewById(android.R.id.button1) != null && view.findViewById(android.R.id.button1) instanceof TextView) {
            view.findViewById(android.R.id.button1).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(android.R.id.button1)).setText(genericButton.title);
            ((TextView)view.findViewById(android.R.id.button1)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onGenericButtonClick();
                }
            });
        }

        if (negativeButton != null && view.findViewById(android.R.id.button2) != null && view.findViewById(android.R.id.button2) instanceof TextView) {
            view.findViewById(android.R.id.button2).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(android.R.id.button2)).setText(negativeButton.title);
            ((TextView)view.findViewById(android.R.id.button2)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNegativeButtonClick();
                }
            });
        }
    }

    protected void onPositiveButtonClick() {
        dismiss();
        if (positiveButton.mListener != null) positiveButton.mListener.onButtonClick(WildcardDialogFragment.this);
    }

    protected void onGenericButtonClick() {
        dismiss();
        if (genericButton.mListener != null) genericButton.mListener.onButtonClick(WildcardDialogFragment.this);
    }

    protected void onNegativeButtonClick() {
        dismiss();
        if (negativeButton.mListener != null) negativeButton.mListener.onButtonClick(WildcardDialogFragment.this);
    }

    public void setPositiveButton(WildcardDialogFragmentButton positiveButton) {
        this.positiveButton = positiveButton;
    }

    public void setNegativeButton(WildcardDialogFragmentButton negativeButton) {
        this.negativeButton = negativeButton;
    }

    public void setGenericButton(WildcardDialogFragmentButton genericButton) {
        this.genericButton = genericButton;
    }

}
