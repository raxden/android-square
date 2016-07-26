package com.raxdenstudios.square.activity.interceptor.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.raxdenstudios.square.activity.interceptor.AutoInflateLayoutInterceptor;
import com.raxdenstudios.square.activity.interceptor.BundleExtrasInterceptor;
import com.raxdenstudios.square.activity.interceptor.ButterKnifeInterceptor;
import com.raxdenstudios.square.activity.interceptor.CheckPlayServicesInterceptor;
import com.raxdenstudios.square.activity.interceptor.CountBackInterceptor;
import com.raxdenstudios.square.activity.interceptor.FragmentContentInterceptor;
import com.raxdenstudios.square.activity.interceptor.InflateLayoutInterceptor;
import com.raxdenstudios.square.activity.interceptor.NavigationDrawerInterceptor;
import com.raxdenstudios.square.activity.interceptor.NetworkInterceptor;
import com.raxdenstudios.square.activity.interceptor.OpenHelperInterceptor;
import com.raxdenstudios.square.activity.interceptor.RaterInterceptor;
import com.raxdenstudios.square.activity.interceptor.ShakeDetectorInterceptor;
import com.raxdenstudios.square.activity.interceptor.TimerInterceptor;
import com.raxdenstudios.square.activity.interceptor.ToolbarInterceptor;
import com.raxdenstudios.square.activity.interceptor.impl.AutoInflateLayoutInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.BundleExtrasInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.ButterKnifeInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.CheckPlayServicesInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.CountBackInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.FragmentContentInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.InflateLayoutInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.NavigationDrawerInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.NetworkInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.OpenHelperInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.RaterInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.ShakeDetectorInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.TimerInterceptorImpl;
import com.raxdenstudios.square.activity.interceptor.impl.ToolbarInterceptorImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agomez on 08/05/2015.
 */
public class InterceptorActivityManager {

    private static final String TAG = InterceptorActivityManager.class.getSimpleName();

    private List<IInterceptorActivity> interceptors;

    public InterceptorActivityManager(Activity activity) {
        if (activity != null) initInterceptors(activity);
    }

    public void onSaveInstanceStateInterceptors(Bundle outState) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorSaveInstanceState(outState);
            }
        }
    }

    public void attachBaseContextInterceptors(Context newBase) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorAttachBaseContextInterceptors(newBase);
            }
        }
    }

    public void onActivityResultInterceptors(int requestCode, int resultCode, Intent data) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public void onConfigurationChangedInterceptors(Configuration configuration) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorConfigurationChanged(configuration);
            }
        }
    }

    public void onCreateInterceptors(Bundle savedInstanceState) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorCreate(savedInstanceState);
            }
        }
    }

    public void onPostCreateInterceptors(Bundle savedInstanceState) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorPostCreate(savedInstanceState);
            }
        }
    }

    public void onPrepareOptionsMenuInterceptors(Menu menu) {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorPrepareOptionsMenu(menu);
            }
        }
    }

    public void onResumeInterceptors() {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorResume();
            }
        }
    }

    public void onStartInterceptors() {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorStart();
            }
        }
    }

    public void onStopInterceptors() {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorStop();
            }
        }
    }

    public void onPauseInterceptors() {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorPause();
            }
        }
    }

    public boolean onBackPressedInterceptors() {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                if (interceptor.onInterceptorBackPressed()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void onDestroyInterceptors() {
        if (interceptors != null) {
            for (IInterceptorActivity interceptor : interceptors) {
                interceptor.onInterceptorDestroy();
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

    private void initInterceptors(Activity activity) {
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
        if (activity instanceof ButterKnifeInterceptor) {
            Log.d(TAG, "....." + ButterKnifeInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new ButterKnifeInterceptorImpl(activity));
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
        if (activity instanceof TimerInterceptor) {
            Log.d(TAG, "....." + TimerInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new TimerInterceptorImpl(activity));
        }
        Log.d(TAG, "======================================================");
    }

}
