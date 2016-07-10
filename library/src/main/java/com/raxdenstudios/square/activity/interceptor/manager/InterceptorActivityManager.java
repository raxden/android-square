package com.raxdenstudios.square.activity.interceptor.manager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.raxdenstudios.square.activity.InterceptorActivity;
import com.raxdenstudios.square.activity.interceptor.AutoInflateLayoutInterceptor;
import com.raxdenstudios.square.activity.interceptor.BundleExtrasInterceptor;
import com.raxdenstudios.square.activity.interceptor.CheckPlayServicesInterceptor;
import com.raxdenstudios.square.activity.interceptor.CountBackInterceptor;
import com.raxdenstudios.square.activity.interceptor.FragmentContentInterceptor;
import com.raxdenstudios.square.activity.interceptor.InflateLayoutInterceptor;
import com.raxdenstudios.square.activity.interceptor.NavigationDrawerInterceptor;
import com.raxdenstudios.square.activity.interceptor.NetworkInterceptor;
import com.raxdenstudios.square.activity.interceptor.OpenHelperInterceptor;
import com.raxdenstudios.square.activity.interceptor.RaterInterceptor;
import com.raxdenstudios.square.activity.interceptor.ShakeDetectorInterceptor;
import com.raxdenstudios.square.activity.interceptor.SplashTimerInterceptor;
import com.raxdenstudios.square.activity.interceptor.ToolbarInterceptor;
import com.raxdenstudios.square.activity.interceptor.impl.AutoInflateLayoutInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.BundleExtrasInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.CheckPlayServicesInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.CountBackInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.FragmentContentInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.InflateLayoutInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.NavigationDrawerInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.NetworkInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.OpenHelperInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.RaterInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.ShakeDetectorInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.SplashTimerInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.ToolbarInterceptorImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agomez on 08/05/2015.
 */
public class InterceptorActivityManager {

    private static final String TAG = InterceptorActivityManager.class.getSimpleName();

    protected List<IInterceptorActivity> interceptors;

    public InterceptorActivityManager(InterceptorActivity activity) {
        if (activity != null) initInterceptors(activity);
    }

    public void onActivityResultInterceptors(Context context, int requestCode, int resultCode, Intent data) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorActivityResult(context, requestCode, resultCode, data);
            }
        }
    }

    public void onConfigurationChangedInterceptors(Context context, Configuration configuration) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorConfigurationChanged(context, configuration);
            }
        }
    }

    public void onCreateInterceptors(Context context, Bundle savedInstanceState) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorCreate(context, savedInstanceState);
            }
        }
    }

    public void onPostCreateInterceptors(Context context, Bundle savedInstanceState) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorPostCreate(context, savedInstanceState);
            }
        }
    }

    public void onPrepareOptionsMenuInterceptors(Context context, Menu menu) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorPrepareOptionsMenu(context, menu);
            }
        }
    }

    public void onResumeInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorResume(context);
            }
        }
    }

    public void onStartInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorStart(context);
            }
        }
    }

    public void onStopInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorStop(context);
            }
        }
    }

    public void onPauseInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorPause(context);
            }
        }
    }

    public boolean onBackPressedInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                if (interceptor.onInterceptorBackPressed(context)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void onDestroyInterceptors(Context context) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorDestroy(context);
            }
        }
    }

    public void addInterceptor(IInterceptorActivity interceptor) {
        interceptors.add(interceptor);
    }

    public List<IInterceptorActivity> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<IInterceptorActivity> interceptors) {
        this.interceptors = interceptors;
    }

    private void initInterceptors(InterceptorActivity activity) {
        Log.d(TAG, "========== Prepare to init activity interceptors ==========");
        interceptors = new ArrayList<>();
        if (activity instanceof InflateLayoutInterceptor) {
            Log.d(TAG, "....." + InflateLayoutInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new InflateLayoutInterceptorImpl(activity));
        }
        if (activity instanceof AutoInflateLayoutInterceptor) {
            Log.d(TAG, "....." + AutoInflateLayoutInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new AutoInflateLayoutInterceptorImpl(activity));
        }
        if (activity instanceof BundleExtrasInterceptor) {
            Log.d(TAG, "....." + BundleExtrasInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new BundleExtrasInterceptorImpl(activity));
        }
        if (activity instanceof CheckPlayServicesInterceptor) {
            Log.d(TAG, "....." + CheckPlayServicesInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new CheckPlayServicesInterceptorImpl(activity));
        }
        if (activity instanceof FragmentContentInterceptor) {
            Log.d(TAG, "....." + FragmentContentInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new FragmentContentInterceptorImpl(activity));
        }
        if (activity instanceof ToolbarInterceptor) {
            Log.d(TAG, "....." + ToolbarInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new ToolbarInterceptorImpl(activity));
        }
        if (activity instanceof NavigationDrawerInterceptor) {
            Log.d(TAG, "....." + NavigationDrawerInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new NavigationDrawerInterceptorImpl(activity));
        }
        if (activity instanceof CountBackInterceptor) {
            Log.d(TAG, "....." + CountBackInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new CountBackInterceptorImpl(activity));
        }
        if (activity instanceof NetworkInterceptor) {
            Log.d(TAG, "....." + NetworkInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new NetworkInterceptorImpl(activity));
        }
        if (activity instanceof OpenHelperInterceptor) {
            Log.d(TAG, "....." + OpenHelperInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new OpenHelperInterceptorImpl(activity));
        }
        if (activity instanceof RaterInterceptor) {
            Log.d(TAG, "....." + RaterInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new RaterInterceptorImpl(activity));
        }
        if (activity instanceof ShakeDetectorInterceptor) {
            Log.d(TAG, "....." + ShakeDetectorInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new ShakeDetectorInterceptorImpl(activity));
        }
        if (activity instanceof SplashTimerInterceptor) {
            Log.d(TAG, "....." + SplashTimerInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new SplashTimerInterceptorImpl(activity));
        }
        Log.d(TAG, "======================================================");
    }

}
