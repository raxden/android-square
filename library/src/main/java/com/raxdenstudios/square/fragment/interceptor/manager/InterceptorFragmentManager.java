package com.raxdenstudios.square.fragment.interceptor.manager;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.fragment.interceptor.AutoInflateViewInterceptor;
import com.raxdenstudios.square.fragment.interceptor.BundleArgumentsInterceptor;
import com.raxdenstudios.square.fragment.interceptor.WebViewInterceptor;
import com.raxdenstudios.square.fragment.interceptor.ZXingScannerInterceptor;
import com.raxdenstudios.square.fragment.interceptor.impl.AutoInflateViewInterceptorImpl;
import com.raxdenstudios.square.fragment.interceptor.impl.BundleArgumentsInterceptorImpl;
import com.raxdenstudios.square.fragment.interceptor.impl.WebViewInterceptorImpl;
import com.raxdenstudios.square.fragment.interceptor.impl.ZXingScannerInterceptorImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agomez on 02/06/2015.
 */
public class InterceptorFragmentManager {

    private static final String TAG = InterceptorFragmentManager.class.getSimpleName();

    private List<IInterceptorFragment> interceptors;
    
    public InterceptorFragmentManager(Fragment fragment) {
        if (fragment != null) initInterceptors(fragment);
    }

    public void onSaveInstanceStateInterceptors(Bundle outState) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorSaveInstanceState(outState);
            }
        }
    }

    public void onActivityResultInterceptors(int requestCode, int resultCode, Intent data) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public void onConfigurationChangedInterceptors(Configuration configuration) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorConfigurationChanged(configuration);
            }
        }
    }

    public void onAttachInterceptors(Activity activity) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorAttach(activity);
            }
        }
    }

    public void onAttachInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorAttach(context);
            }
        }
    }

    public void onCreateInterceptors(Bundle savedInstanceState) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorCreate(savedInstanceState);
            }
        }
    }

    public View onCreateViewInterceptors(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                View view = interceptor.onInterceptorCreateView(inflater, container, savedInstanceState);
                if (view != null) {
                    return view;
                }
            }
        }
        return null;
    }

    public void onViewCreatedInterceptors(View view, Bundle savedInstanceState) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorViewCreated(view, savedInstanceState);
            }
        }
    }

    public void onActivityCreatedInterceptors(Bundle savedInstanceState) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorActivityCreated(savedInstanceState);
            }
        }
    }

    public void onResumeInterceptors() {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorResume();
            }
        }
    }

    public void onStartInterceptors() {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorStart();
            }
        }
    }

    public void onStopInterceptors() {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorStop();
            }
        }
    }

    public void onPauseInterceptors() {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorPause();
            }
        }
    }

    public void onDestroyViewInterceptors() {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorDestroyView();
            }
        }
    }

    public void onDestroyInterceptors() {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorDestroy();
            }
        }
    }

    public void onDetachInterceptors() {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorDetach();
            }
        }
    }

    public void addInterceptor(IInterceptorFragment interceptor) {
        interceptors.add(interceptor);
    }

    public List<IInterceptorFragment> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<IInterceptorFragment> interceptors) {
        this.interceptors = interceptors;
    }

    private void initInterceptors(Fragment fragment) {
        interceptors = new ArrayList<>();
        Log.d(TAG, "========== Prepare to init fragment interceptors ==========");
        if (fragment instanceof BundleArgumentsInterceptor) {
            Log.d(TAG, "....."+BundleArgumentsInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new BundleArgumentsInterceptorImpl(fragment));
        }
        if (fragment instanceof AutoInflateViewInterceptor) {
            Log.d(TAG, "....."+AutoInflateViewInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new AutoInflateViewInterceptorImpl(fragment));
        }
        if (fragment instanceof WebViewInterceptor) {
            Log.d(TAG, "....."+WebViewInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new WebViewInterceptorImpl(fragment));
        }
        if (fragment instanceof ZXingScannerInterceptor) {
            Log.d(TAG, "....."+ZXingScannerInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new ZXingScannerInterceptorImpl(fragment));
        }
        Log.d(TAG, "======================================================");
    }

}
