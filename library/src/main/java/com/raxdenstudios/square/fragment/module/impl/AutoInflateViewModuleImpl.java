package com.raxdenstudios.square.fragment.module.impl;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.fragment.ModularFragment;
import com.raxdenstudios.square.fragment.module.AutoInflateViewModule;
import com.raxdenstudios.square.fragment.module.manager.ModuleFragmentImpl;
import com.raxdenstudios.commons.util.ResourceUtils;
import com.raxdenstudios.commons.util.StringUtils;

import java.util.Locale;

/**
 * Created by agomez on 02/06/2015.
 */
public class AutoInflateViewModuleImpl extends ModuleFragmentImpl implements AutoInflateViewModule.AutoInflateViewModuleListener {

    private static final String TAG = AutoInflateViewModuleImpl.class.getSimpleName();

    private AutoInflateViewModule mCallbacks;
    private View mInflateView;

    public AutoInflateViewModuleImpl(ModularFragment fragment) {
        if (!(fragment instanceof AutoInflateViewModule)) {
            throw new IllegalStateException("Fragment must implement AutoInflateViewModule.");
        }
        mCallbacks = (AutoInflateViewModule)fragment;
    }

    @Override
    public View onModuleCreateView(Context context, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflateView =  inflateLayout(context, inflater);
        if (mCallbacks != null) mCallbacks.onModuleLoaded(this);
        return mInflateView;
    }

    private View inflateLayout(Context context, LayoutInflater inflater) {
        if (context != null) {
            String layoutToSearch = StringUtils.join(StringUtils.uncapitalize(((ModularFragment)mCallbacks).getClass().getSimpleName()).split("(?=\\p{Upper})"), "_").toLowerCase(Locale.getDefault());
            int layoutId = ResourceUtils.getLayoutId(context, layoutToSearch);
            if (layoutId > 0) {
                return inflater.inflate(layoutId, null);
            } else {
                Log.w(TAG, layoutToSearch+" not found!");
            }
        }
        return null;
    }

    @Override
    public View getView() {
        return mInflateView;
    }

}
