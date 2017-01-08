package com.raxdenstudios.square.interceptor;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.raxdenstudios.mvp.presenter.Presenter;
import com.raxdenstudios.mvp.view.IView;
import com.raxdenstudios.square.interceptor.type.activity.AutoInflateLayoutActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.BundleExtrasActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.CalligraphyActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.CastActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.CastSessionActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.CompositeSubscriptionActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.CountBackActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.FragmentContentActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.FragmentStatePagerActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.FullScreenInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.IcepickActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.NavigationDrawerActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.NetworkActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.OpenHelperActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.RaterActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.ShakeDetectorActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.TelephonyInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.TimerActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.ToolbarActivityInterceptor;
import com.raxdenstudios.square.interceptor.type.activity.impl.AutoInflateLayoutInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.BundleExtrasInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.CastInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.CastSessionInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.CountBackInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.FragmentContentInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.FragmentStatePagerInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.FullScreenInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.IcepickInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.NavigationDrawerInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.NetworkInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.OpenHelperInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.RaterInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.ShakeDetectorInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.TelephonyInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.activity.impl.ToolbarInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.application.CalligraphyApplicationInterceptor;
import com.raxdenstudios.square.interceptor.type.application.FabricApplicationInterceptor;
import com.raxdenstudios.square.interceptor.type.application.JodaTimeApplicationInterceptor;
import com.raxdenstudios.square.interceptor.type.application.TimberApplicationInterceptor;
import com.raxdenstudios.square.interceptor.type.application.impl.FabricInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.application.impl.JodaTimeInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.application.impl.TimberInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.fragment.AutoInflateViewFragmentInterceptor;
import com.raxdenstudios.square.interceptor.type.fragment.BundleArgumentsFragmentInterceptor;
import com.raxdenstudios.square.interceptor.type.fragment.TimerFragmentInterceptor;
import com.raxdenstudios.square.interceptor.type.fragment.WebViewFragmentInterceptor;
import com.raxdenstudios.square.interceptor.type.fragment.ZXingScannerFragmentInterceptor;
import com.raxdenstudios.square.interceptor.type.fragment.impl.AutoInflateViewInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.fragment.impl.BundleArgumentsInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.fragment.impl.WebViewInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.fragment.impl.ZXingScannerInterceptorImpl;
import com.raxdenstudios.square.interceptor.type.presenter.CompositeSubscriptionPresenterInterceptor;
import com.raxdenstudios.square.interceptor.type.presenter.TimerPresenterInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ángel Gómez
 *
 * Interceptor Factory
 */
public class InterceptorFactory {

    private static final String TAG = InterceptorFactory.class.getSimpleName();

    public static <T> List<Interceptor> buildInterceptors(T type) {
        List<Interceptor> interceptors = new ArrayList<>();
        if (type instanceof MultiDexApplication) {
            interceptors = buildInterceptors((MultiDexApplication) type);
        } else if (type instanceof Application) {
            interceptors = buildInterceptors((Application) type);
        } else if (type instanceof Activity) {
            interceptors = buildInterceptors((Activity) type);
        } else if (type instanceof Fragment) {
            interceptors = buildInterceptors((Fragment) type);
        } else if (type instanceof Presenter) {
            interceptors = buildInterceptors((Presenter) type);
        }
        return interceptors;
    }

    private static List<Interceptor> buildInterceptors(Activity activity) {
        Log.d(TAG, "========== Prepare to init "+activity.getClass().getSimpleName()+" activity interceptors ==========");
        List<Interceptor> interceptors = new ArrayList<>();
        if (activity instanceof  FullScreenInterceptor) {
            Log.d(TAG, "....." + FullScreenInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new FullScreenInterceptorImpl(activity));
        }
        if (activity instanceof  TelephonyInterceptor) {
            Log.d(TAG, "....." + TelephonyInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new TelephonyInterceptorImpl(activity));
        }
        if (activity instanceof CalligraphyActivityInterceptor) {
            Log.d(TAG, "....." + CalligraphyActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new com.raxdenstudios.square.interceptor.type.activity.impl.CalligraphyInterceptorImpl(activity));
        }
        if (activity instanceof BundleExtrasActivityInterceptor) {
            Log.d(TAG, "....." + BundleExtrasActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new BundleExtrasInterceptorImpl(activity));
        }
        if (activity instanceof AutoInflateLayoutActivityInterceptor) {
            Log.d(TAG, "....." + AutoInflateLayoutActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new AutoInflateLayoutInterceptorImpl(activity));
        }
        if (activity instanceof com.raxdenstudios.square.butterknife.interceptor.type.activity.ButterKnifeActivityInterceptor) {
            Log.d(TAG, "....." + com.raxdenstudios.square.butterknife.interceptor.type.activity.ButterKnifeActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new com.raxdenstudios.square.butterknife.interceptor.type.activity.impl.ButterKnifeInterceptorImpl(activity));
        }
        if (activity instanceof IcepickActivityInterceptor) {
            Log.d(TAG, "....." + IcepickActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new IcepickInterceptorImpl(activity));
        }
        if (activity instanceof FragmentContentActivityInterceptor) {
            Log.d(TAG, "....." + FragmentContentActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new FragmentContentInterceptorImpl(activity));
        }
        if (activity instanceof FragmentStatePagerActivityInterceptor) {
            Log.d(TAG, "....." + FragmentStatePagerActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new FragmentStatePagerInterceptorImpl(activity));
        }
        if (activity instanceof ToolbarActivityInterceptor) {
            Log.d(TAG, "....." + ToolbarActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new ToolbarInterceptorImpl(activity));
        }
        if (activity instanceof NavigationDrawerActivityInterceptor) {
            Log.d(TAG, "....." + NavigationDrawerActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new NavigationDrawerInterceptorImpl(activity));
        }
        if (activity instanceof CompositeSubscriptionActivityInterceptor) {
            Log.d(TAG, "....." + CompositeSubscriptionActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new com.raxdenstudios.square.interceptor.type.activity.impl.CompositeSubscriptionInterceptorImpl(activity));
        }
        if (activity instanceof CountBackActivityInterceptor) {
            Log.d(TAG, "....." + CountBackActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new CountBackInterceptorImpl(activity));
        }
        if (activity instanceof NetworkActivityInterceptor) {
            Log.d(TAG, "....." + NetworkActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new NetworkInterceptorImpl(activity));
        }
        if (activity instanceof OpenHelperActivityInterceptor) {
            Log.d(TAG, "....." + OpenHelperActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new OpenHelperInterceptorImpl(activity));
        }
        if (activity instanceof CastActivityInterceptor) {
            Log.d(TAG, "....." + CastActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new CastInterceptorImpl(activity));
        }
        if (activity instanceof CastSessionActivityInterceptor) {
            Log.d(TAG, "....." + CastSessionActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new CastSessionInterceptorImpl(activity));
        }
        if (activity instanceof RaterActivityInterceptor) {
            Log.d(TAG, "....." + RaterActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new RaterInterceptorImpl(activity));
        }
        if (activity instanceof ShakeDetectorActivityInterceptor) {
            Log.d(TAG, "....." + ShakeDetectorActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new ShakeDetectorInterceptorImpl(activity));
        }
        if (activity instanceof TimerActivityInterceptor) {
            Log.d(TAG, "....." + TimerActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new com.raxdenstudios.square.interceptor.type.activity.impl.TimerInterceptorImpl(activity));
        }
        Log.d(TAG, "======================================================");
        return interceptors;
    }

    private static List<Interceptor> buildInterceptors(Application application) {
        Log.d(TAG, "========== Prepare to init "+application.getClass().getSimpleName()+" application interceptors ==========");
        List<Interceptor> interceptors = new ArrayList<>();
        if (application instanceof TimberApplicationInterceptor) {
            interceptors.add(new TimberInterceptorImpl(application));
        }
        if (application instanceof FabricApplicationInterceptor) {
            interceptors.add(new FabricInterceptorImpl(application));
        }
        if (application instanceof JodaTimeApplicationInterceptor) {
            interceptors.add(new JodaTimeInterceptorImpl(application));
        }
        if (application instanceof CalligraphyApplicationInterceptor) {
            interceptors.add(new com.raxdenstudios.square.interceptor.type.application.impl.CalligraphyInterceptorImpl(application));
        }
        Log.d(TAG, "======================================================");
        return interceptors;
    }

    private static List<Interceptor> buildInterceptors(MultiDexApplication application) {
        return buildInterceptors((Application) application);
    }

    private static List<Interceptor> buildInterceptors(Fragment fragment) {
        Log.d(TAG, "========== Prepare to init "+fragment.getClass().getSimpleName()+" fragment interceptors ==========");
        List<Interceptor> interceptors = new ArrayList<>();
        if (fragment instanceof BundleArgumentsFragmentInterceptor) {
            Log.d(TAG, "....."+BundleArgumentsFragmentInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new BundleArgumentsInterceptorImpl(fragment));
        }
        if (fragment instanceof AutoInflateViewFragmentInterceptor) {
            Log.d(TAG, "....."+AutoInflateViewFragmentInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new AutoInflateViewInterceptorImpl(fragment));
        }
        if (fragment instanceof com.raxdenstudios.square.butterknife.interceptor.type.fragment.ButterKnifeFragmentInterceptor) {
            Log.d(TAG, "....." + com.raxdenstudios.square.butterknife.interceptor.type.fragment.ButterKnifeFragmentInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new com.raxdenstudios.square.butterknife.interceptor.type.fragment.impl.ButterKnifeInterceptorImpl(fragment));
        }
        if (fragment instanceof CompositeSubscriptionActivityInterceptor) {
            Log.d(TAG, "....." + CompositeSubscriptionActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new com.raxdenstudios.square.interceptor.type.fragment.impl.CompositeSubscriptionInterceptorImpl(fragment));
        }
        if (fragment instanceof TimerFragmentInterceptor) {
            Log.d(TAG, "....."+TimerFragmentInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new com.raxdenstudios.square.interceptor.type.fragment.impl.TimerInterceptorImpl(fragment));
        }
        if (fragment instanceof WebViewFragmentInterceptor) {
            Log.d(TAG, "....."+WebViewFragmentInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new WebViewInterceptorImpl(fragment));
        }
        if (fragment instanceof ZXingScannerFragmentInterceptor) {
            Log.d(TAG, "....."+ZXingScannerFragmentInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new ZXingScannerInterceptorImpl(fragment));
        }
        Log.d(TAG, "======================================================");
        return interceptors;
    }

    private static <TView extends IView> List<Interceptor> buildInterceptors(Presenter<TView> presenter) {
        List<Interceptor> interceptors = new ArrayList<>();
        if (presenter instanceof CompositeSubscriptionPresenterInterceptor) {
            Log.d(TAG, "....." + TimerActivityInterceptor.class.getSimpleName() + " loaded!");
            interceptors.add(new com.raxdenstudios.square.interceptor.type.presenter.impl.CompositeSubscriptionInterceptorImpl<TView>(presenter));
        }
        if (presenter instanceof TimerPresenterInterceptor) {
            Log.d(TAG, "....."+TimerPresenterInterceptor.class.getSimpleName()+" loaded!");
            interceptors.add(new com.raxdenstudios.square.interceptor.type.presenter.impl.TimerInterceptorImpl(presenter));
        }
        return interceptors;
    }

}
