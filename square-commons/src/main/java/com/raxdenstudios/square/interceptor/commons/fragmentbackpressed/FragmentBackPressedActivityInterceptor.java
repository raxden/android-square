package com.raxdenstudios.square.interceptor.commons.fragmentbackpressed;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

/**
 * Created by Ángel Gómez on 17/06/2017.
 */

public class FragmentBackPressedActivityInterceptor extends ActivityInterceptor<FragmentBackPressedInterceptorCallback> implements FragmentBackPressedInterceptor {

    private List<OnFragmentBackPressedListener> mOnBackPressedListeners;

    public FragmentBackPressedActivityInterceptor(@NonNull FragmentActivity activity) {
        super(activity);
    }

    public FragmentBackPressedActivityInterceptor(@NonNull FragmentActivity activity, @NonNull FragmentBackPressedInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void addOnFragmentBackPressedListener(OnFragmentBackPressedListener listener) {
        if (mOnBackPressedListeners == null) {
            mOnBackPressedListeners = new ArrayList<>();
        }
        mOnBackPressedListeners.add(listener);
    }

    @Override
    public void removeOnFragmentBackPressedListener(OnFragmentBackPressedListener listener) {
        if (mOnBackPressedListeners != null) {
            mOnBackPressedListeners.remove(listener);
        }
    }

    @Override
    public boolean onBackPressed() {
        if (mOnBackPressedListeners != null) {
            for (OnFragmentBackPressedListener listener : mOnBackPressedListeners) {
                if (listener != null && listener.onBackPressed()) {
                    return true;
                }
            }
        }
        return super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mOnBackPressedListeners != null) {
            mOnBackPressedListeners.clear();
            mOnBackPressedListeners = null;
        }
    }

}
