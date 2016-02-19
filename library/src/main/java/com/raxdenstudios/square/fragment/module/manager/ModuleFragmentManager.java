package com.raxdenstudios.square.fragment.module.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.activity.ModularActivity;
import com.raxdenstudios.square.activity.module.manager.ModuleActivity;
import com.raxdenstudios.square.activity.module.manager.ModuleActivityManager;
import com.raxdenstudios.square.fragment.ModularFragment;
import com.raxdenstudios.square.fragment.module.AutoInflateViewModule;
import com.raxdenstudios.square.fragment.module.BundleArgumentsModule;
import com.raxdenstudios.square.fragment.module.FlurryModule;
import com.raxdenstudios.square.fragment.module.GoogleAnalyticsModule;
import com.raxdenstudios.square.fragment.module.ListViewModule;
import com.raxdenstudios.square.fragment.module.ZXingScannerModule;
import com.raxdenstudios.square.fragment.module.impl.AutoInflateViewModuleImpl;
import com.raxdenstudios.square.fragment.module.impl.BundleArgumentsModuleImpl;
import com.raxdenstudios.square.fragment.module.impl.FlurryModuleImpl;
import com.raxdenstudios.square.fragment.module.impl.GoogleAnalyticsModuleImpl;
import com.raxdenstudios.square.fragment.module.impl.ListViewModuleImpl;
import com.raxdenstudios.square.fragment.module.impl.ZXingScannerModuleImpl;

import java.util.ArrayList;

/**
 * Created by agomez on 02/06/2015.
 */
public class ModuleFragmentManager extends ModuleActivityManager {

    private static final String TAG = ModuleFragmentManager.class.getSimpleName();

    private ModuleFragmentManager(ModularActivity activity) {
        super(activity);
    }

    public ModuleFragmentManager(ModularFragment fragment) {
        super(null);
        if (fragment != null) initModules(fragment);
    }

    public void onAttachModules(Activity activity) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                ((ModuleFragment)module).onModuleAttach(activity);
            }
        }
    }

    public View onCreateViewModules(Context context, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                View view = ((ModuleFragment)module).onModuleCreateView(context, inflater, container, savedInstanceState);
                if (view != null) {
                    return view;
                }
            }
        }
        return null;
    }

    public void onViewCreatedModules(Context context, View view, Bundle savedInstanceState) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                ((ModuleFragment)module).onModuleViewCreated(context, view, savedInstanceState);
            }
        }
    }

    public void onActivityCreatedModules(Context context, Bundle savedInstanceState) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                ((ModuleFragment)module).onModuleActivityCreated(context, savedInstanceState);
            }
        }
    }

    public void onLoadContentModules(Context context) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                ((ModuleFragment)module).onModuleLoadContent(context);
            }
        }
    }

    public void onDetachModules(Context context) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                ((ModuleFragment)module).onModuleDetach(context);
            }
        }
    }

    public void onRefreshContentModules(Context context) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                ((ModuleFragment)module).onRefreshContent(context);
            }
        }
    }

    public void onProgressShowModules(Context context, String progressLabel) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                ((ModuleFragment)module).onProgressShow(context, progressLabel);
            }
        }
    }

    public void onNoticeModules(Context context, int id, String title, String message) {
        if (modules != null) {
            for (ModuleActivity module : modules) {
                ((ModuleFragment)module).onNotice(context, id, title, message);
            }
        }
    }

    private void initModules(ModularFragment fragment) {
        modules = new ArrayList<>();
        Log.d(TAG, "========== Prepare to init fragment modules ==========");
        if (fragment instanceof BundleArgumentsModule) {
            Log.d(TAG, "....."+BundleArgumentsModule.class.getSimpleName()+" loaded!");
            modules.add(new BundleArgumentsModuleImpl(fragment));
        }
        if (fragment instanceof AutoInflateViewModule) {
            Log.d(TAG, "....."+AutoInflateViewModule.class.getSimpleName()+" loaded!");
            modules.add(new AutoInflateViewModuleImpl(fragment));
        }
        if (fragment instanceof FlurryModule) {
            Log.d(TAG, "....."+FlurryModule.class.getSimpleName()+" loaded!");
            modules.add(new FlurryModuleImpl(fragment));
        }
        if (fragment instanceof GoogleAnalyticsModule) {
            Log.d(TAG, "....."+GoogleAnalyticsModule.class.getSimpleName()+" loaded!");
            modules.add(new GoogleAnalyticsModuleImpl(fragment));
        }
        if (fragment instanceof ListViewModule) {
            Log.d(TAG, "....."+ListViewModule.class.getSimpleName()+" loaded!");
            modules.add(new ListViewModuleImpl(fragment));
        }
        if (fragment instanceof ZXingScannerModule) {
            Log.d(TAG, "....."+ZXingScannerModule.class.getSimpleName()+" loaded!");
            modules.add(new ZXingScannerModuleImpl(fragment));
        }
        Log.d(TAG, "======================================================");
    }

}
