package com.raxdenstudios.square.fragment.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.R;
import com.raxdenstudios.commons.util.ResourceUtils;
import com.raxdenstudios.commons.util.StringUtils;

import java.util.Locale;

public class DialogFragmentImproved extends DialogFragment {

	private static final String TAG = DialogFragmentImproved.class.getSimpleName();

    protected int layout;
    protected int style;
    protected int theme;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onHandleArguments(getArguments());

        setStyle(style, theme);
    }

    public void onHandleArguments(Bundle arguments) {
        layout = arguments != null && arguments.containsKey("layout") ? arguments.getInt("layout") : 0;
        style = arguments != null && arguments.containsKey("style") ? arguments.getInt("style") : DialogFragment.STYLE_NO_TITLE;
        theme = arguments != null && arguments.containsKey("theme") ? arguments.getInt("theme") : R.style.Theme_AppCompat_Light_Dialog;
    }

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		return dialog;
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateFragmentLayout(inflater);
    }

    /* Support methods */

    /**
     * Override this method to init your layout.
     * @return
     */
    protected View inflateFragmentLayout(LayoutInflater inflater) {
        int layoutId = layout > 0 ? layout : ResourceUtils.getLayoutId(getActivity(), StringUtils.join(StringUtils.uncapitalize(this.getClass().getSimpleName()).split("(?=\\p{Upper})"), "_").toLowerCase(Locale.getDefault()));
        if (layoutId > 0) {
            return inflater.inflate(layoutId, null);
        }
        return null;
    }

}
