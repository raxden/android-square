package com.raxdenstudios.square.activity.module.manager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.AutoInflateLayoutModule;
import com.raxdenstudios.square.activity.module.BundleExtrasModule;
import com.raxdenstudios.square.activity.module.CheckPlayServicesModule;
import com.raxdenstudios.square.activity.module.CountBackModule;
import com.raxdenstudios.square.activity.module.FlurryModule;
import com.raxdenstudios.square.activity.module.FragmentContentModule;
import com.raxdenstudios.square.activity.module.GCMModule;
import com.raxdenstudios.square.activity.module.GCMessageModule;
import com.raxdenstudios.square.activity.module.GoogleAnalyticsModule;
import com.raxdenstudios.square.activity.module.InflateLayoutModule;
import com.raxdenstudios.square.activity.module.LoaderViewModule;
import com.raxdenstudios.square.activity.module.NavigationDrawerModule;
import com.raxdenstudios.square.activity.module.NetworkModule;
import com.raxdenstudios.square.activity.module.OpenHelperModule;
import com.raxdenstudios.square.activity.module.RaterModule;
import com.raxdenstudios.square.activity.module.ShakeDetectorModule;
import com.raxdenstudios.square.activity.module.SplashTimerModule;
import com.raxdenstudios.square.activity.module.ToolbarModule;
import com.raxdenstudios.square.activity.module.impl.AutoInflateLayoutModuleImpl;
import com.raxdenstudios.square.activity.module.impl.BundleExtrasModuleImpl;
import com.raxdenstudios.square.activity.module.impl.CheckPlayServicesModuleImpl;
import com.raxdenstudios.square.activity.module.impl.CountBackModuleImpl;
import com.raxdenstudios.square.activity.module.impl.FlurryModuleImpl;
import com.raxdenstudios.square.activity.module.impl.FragmentContentModuleImpl;
import com.raxdenstudios.square.activity.module.impl.GCMModuleImpl;
import com.raxdenstudios.square.activity.module.impl.GCMessageModuleImpl;
import com.raxdenstudios.square.activity.module.impl.GoogleAnalyticsModuleImpl;
import com.raxdenstudios.square.activity.module.impl.InflateLayoutModuleImpl;
import com.raxdenstudios.square.activity.module.impl.LoaderViewModuleImpl;
import com.raxdenstudios.square.activity.module.impl.NavigationDrawerModuleImpl;
import com.raxdenstudios.square.activity.module.impl.NetworkModuleImpl;
import com.raxdenstudios.square.activity.module.impl.OpenHelperModuleImpl;
import com.raxdenstudios.square.activity.module.impl.RaterModuleImpl;
import com.raxdenstudios.square.activity.module.impl.ShakeDetectorModuleImpl;
import com.raxdenstudios.square.activity.module.impl.SplashTimerModuleImpl;
import com.raxdenstudios.square.activity.module.impl.ToolbarModuleImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by agomez on 08/05/2015.
 */
public class ModuleActivityManager {

    private static final String TAG = ModuleActivityManager.class.getSimpleName();

    protected List<ModuleActivity> modules;

    public ModuleActivityManager(ModularActivity activity) {
        if (activity != null) initModules(activity);
    }

    public void onActivityResultModules(Context context, int requestCode, int resultCode, Intent data) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                module.onModuleActivityResult(context, requestCode, resultCode, data);
            }
        }
    }

    public void onConfigurationChangedModules(Context context, Configuration configuration) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                module.onModuleConfigurationChanged(context, configuration);
            }
        }
    }

    public void onCreateModules(Context context, Bundle savedInstanceState) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                module.onModuleCreate(context, savedInstanceState);
            }
        }
    }

    public void onPostCreateModules(Context context, Bundle savedInstanceState) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                module.onModulePostCreate(context, savedInstanceState);
            }
        }
    }

    public void onPrepareOptionsMenuModules(Context context, Menu menu) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                module.onModulePrepareOptionsMenu(context, menu);
            }
        }
    }

    public void onResumeModules(Context context) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                module.onModuleResume(context);
            }
        }
    }

    public void onStartModules(Context context) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                module.onModuleStart(context);
            }
        }
    }

    public void onStopModules(Context context) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                module.onModuleStop(context);
            }
        }
    }

    public void onPauseModules(Context context) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                module.onModulePause(context);
            }
        }
    }

    public boolean onBackPressedModules(Context context) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                if (module.onModuleBackPressed(context)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void onProgressShowModules(Context context, String progressLabel) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                module.onProgressShow(context, progressLabel);
            }
        }
    }

    public void onProgressHideModules(Context context) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                module.onProgressHide(context);
            }
        }
    }

    public void onDestroyModules(Context context) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                module.onModuleDestroy(context);
            }
        }
    }

    public void onDestroyViewModules(Context context) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                module.onModuleDestroyView(context);
            }
        }
    }

    public void addModule(ModuleActivity module) {
        modules.add(module);
    }

    public List<ModuleActivity> getModules() {
        return modules;
    }

    public void setModules(List<ModuleActivity> modules) {
        this.modules = modules;
    }

    private void initModules(ModularActivity activity) {
        Log.d(TAG, "========== Prepare to init activity modules ==========");
        modules = new ArrayList<>();
        if (activity instanceof InflateLayoutModule) {
            Log.d(TAG, "....."+InflateLayoutModule.class.getSimpleName()+" loaded!");
            modules.add(new InflateLayoutModuleImpl(activity));
        }
        if (activity instanceof AutoInflateLayoutModule) {
            Log.d(TAG, "....."+AutoInflateLayoutModule.class.getSimpleName()+" loaded!");
            modules.add(new AutoInflateLayoutModuleImpl(activity));
        }
        if (activity instanceof BundleExtrasModule) {
            Log.d(TAG, "....."+BundleExtrasModule.class.getSimpleName()+" loaded!");
            modules.add(new BundleExtrasModuleImpl(activity));
        }
        if (activity instanceof LoaderViewModule) {
            Log.d(TAG, "....."+LoaderViewModule.class.getSimpleName()+" loaded!");
            modules.add(new LoaderViewModuleImpl(activity));
        }
        if (activity instanceof CheckPlayServicesModule) {
            Log.d(TAG, "....."+CheckPlayServicesModule.class.getSimpleName()+" loaded!");
            modules.add(new CheckPlayServicesModuleImpl(activity));
        }
        if (activity instanceof GCMModule) {
            Log.d(TAG, "....."+GCMModule.class.getSimpleName()+" loaded!");
            modules.add(new GCMModuleImpl(activity));
        }
        if (activity instanceof GCMessageModule) {
            Log.d(TAG, "....."+GCMessageModule.class.getSimpleName()+" loaded!");
            modules.add(new GCMessageModuleImpl(activity));
        }
        if (activity instanceof FragmentContentModule) {
            Log.d(TAG, "....."+FragmentContentModule.class.getSimpleName()+" loaded!");
            modules.add(new FragmentContentModuleImpl(activity));
        }
        if (activity instanceof ToolbarModule) {
            Log.d(TAG, "....."+ToolbarModule.class.getSimpleName()+" loaded!");
            modules.add(new ToolbarModuleImpl(activity));
        }
        if (activity instanceof NavigationDrawerModule) {
            Log.d(TAG, "....."+NavigationDrawerModule.class.getSimpleName()+" loaded!");
            modules.add(new NavigationDrawerModuleImpl(activity));
        }
        if (activity instanceof GoogleAnalyticsModule) {
            Log.d(TAG, "....."+GoogleAnalyticsModule.class.getSimpleName()+" loaded!");
            modules.add(new GoogleAnalyticsModuleImpl(activity));
        }
        if (activity instanceof FlurryModule) {
            Log.d(TAG, "....."+FlurryModule.class.getSimpleName()+" loaded!");
            modules.add(new FlurryModuleImpl(activity));
        }
        if (activity instanceof CountBackModule) {
            Log.d(TAG, "....."+CountBackModule.class.getSimpleName()+" loaded!");
            modules.add(new CountBackModuleImpl(activity));
        }
        if (activity instanceof NetworkModule) {
            Log.d(TAG, "....."+NetworkModule.class.getSimpleName()+" loaded!");
            modules.add(new NetworkModuleImpl(activity));
        }
        if (activity instanceof OpenHelperModule) {
            Log.d(TAG, "....."+OpenHelperModule.class.getSimpleName()+" loaded!");
            modules.add(new OpenHelperModuleImpl(activity));
        }
        if (activity instanceof RaterModule) {
            Log.d(TAG, "....."+RaterModule.class.getSimpleName()+" loaded!");
            modules.add(new RaterModuleImpl(activity));
        }
        if (activity instanceof ShakeDetectorModule) {
            Log.d(TAG, "....."+ShakeDetectorModule.class.getSimpleName()+" loaded!");
            modules.add(new ShakeDetectorModuleImpl(activity));
        }
        if (activity instanceof SplashTimerModule) {
            Log.d(TAG, "....."+SplashTimerModule.class.getSimpleName()+" loaded!");
            modules.add(new SplashTimerModuleImpl(activity));
        }
        Log.d(TAG, "======================================================");
    }

}
