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

import com.raxdenstudios.square.fragment.InterceptorDialogFragment;
import com.raxdenstudios.square.fragment.InterceptorFragment;
import com.raxdenstudios.square.fragment.interceptor.AutoInflateViewInterceptor;
import com.raxdenstudios.square.fragment.interceptor.BundleArgumentsInterceptor;
import com.raxdenstudios.square.fragment.interceptor.ZXingScannerInterceptor;
import com.raxdenstudios.square.fragment.interceptor.impl.AutoInflateViewInterceptorImpl;
import com.raxdenstudios.square.fragment.interceptor.impl.BundleArgumentsInterceptorImpl;
import com.raxdenstudios.square.fragment.interceptor.impl.ZXingScannerInterceptorImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agomez on 02/06/2015.
 */
public class InterceptorFragmentManager {

    private static final String TAG = InterceptorFragmentManager.class.getSimpleName();

    protected List<IInterceptorFragment> interceptors;
    
    public InterceptorFragmentManager(InterceptorFragment fragment) {
        if (fragment != null) initInterceptors(fragment);
    }

    public InterceptorFragmentManager(InterceptorDialogFragment fragment) {
        if (fragment != null) initInterceptors(fragment);
    }

    public void onActivityResultInterceptors(Context context, int requestCode, int resultCode, Intent data) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorActivityResult(context, requestCode, resultCode, data);
            }
        }
    }

    public void onConfigurationChangedInterceptors(Context context, Configuration configuration) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorConfigurationChanged(context, configuration);
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

    public void onCreateInterceptors(Context context, Bundle savedInstanceState) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorCreate(context, savedInstanceState);
            }
        }
    }

    public View onCreateViewInterceptors(Context context, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                View view = interceptor.onInterceptorCreateView(context, inflater, container, savedInstanceState);
                if (view != null) {
                    return view;
                }
            }
        }
        return null;
    }

    public void onViewCreatedInterceptors(Context context, View view, Bundle savedInstanceState) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorViewCreated(context, view, savedInstanceState);
            }
        }
    }

    public void onActivityCreatedInterceptors(Context context, Bundle savedInstanceState) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorActivityCreated(context, savedInstanceState);
            }
        }
    }

    public void onResumeInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorResume(context);
            }
        }
    }

    public void onStartInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorStart(context);
            }
        }
    }

    public void onStopInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorStop(context);
            }
        }
    }

    public void onPauseInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorPause(context);
            }
        }
    }

    public void onDestroyViewInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorDestroyView(context);
            }
        }
    }

    public void onDestroyInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorDestroy(context);
            }
        }
    }

    public void onDetachInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorFragment interceptor : interceptors) {
                interceptor.onInterceptorDetach(context);
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
        if (fragment instanceof ZXingScannerInterceptor) {
            Log.d(TAG, "....."+ZXingScannerInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new ZXingScannerInterceptorImpl(fragment));
        }
        Log.d(TAG, "======================================================");
    }

}
