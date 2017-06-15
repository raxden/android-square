package com.raxdenstudios.square.interceptor.cast;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by Raxden on 13/12/2016.
 */

public class CastSessionActivityInterceptorImpl extends ActivityInterceptor<CastSessionInterceptorCallback> implements CastSessionInterceptor {

    private CastContext mCastContext;
    private CastSession mCastSession;
    private SessionManagerListener<CastSession> mSessionManagerListener;

    public CastSessionActivityInterceptorImpl(@NonNull Activity activity) {
        super(activity);
    }

    public CastSessionActivityInterceptorImpl(@NonNull Activity activity, @NonNull CastSessionInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupCastListener();
        mCastContext = CastContext.getSharedInstance(mActivity);
        mCastContext.registerLifecycleCallbacksBeforeIceCreamSandwich((FragmentActivity)mActivity, savedInstanceState);
        mCastSession = mCastContext.getSessionManager().getCurrentCastSession();
        if (mCastSession != null) {
            mCallback.onCastConnected(mCastSession);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCastContext != null) {
            mCastContext.getSessionManager()
                    .addSessionManagerListener(mSessionManagerListener, CastSession.class);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mCastContext != null) {
            mCastContext.getSessionManager()
                    .removeSessionManagerListener(mSessionManagerListener, CastSession.class);
        }
    }

    private void setupCastListener() {
        mSessionManagerListener = new SessionManagerListener<CastSession>() {

            @Override
            public void onSessionEnded(CastSession session, int error) {
                onApplicationDisconnected();
            }

            @Override
            public void onSessionResumed(CastSession session, boolean wasSuspended) {
                onApplicationConnected(session);
            }

            @Override
            public void onSessionResumeFailed(CastSession session, int error) {
                onApplicationDisconnected();
            }

            @Override
            public void onSessionStarted(CastSession session, String sessionId) {
                onApplicationConnected(session);
            }

            @Override
            public void onSessionStartFailed(CastSession session, int error) {
                onApplicationDisconnected();
            }

            @Override
            public void onSessionStarting(CastSession session) {
            }

            @Override
            public void onSessionEnding(CastSession session) {
            }

            @Override
            public void onSessionResuming(CastSession session, String sessionId) {
            }

            @Override
            public void onSessionSuspended(CastSession session, int reason) {
            }

            private void onApplicationConnected(CastSession castSession) {
                mCastSession = castSession;
                if (mCallback != null) {
                    mCallback.onCastConnected(castSession);
                }
            }

            private void onApplicationDisconnected() {
                if (mCallback != null) {
                    mCallback.onCastDisconnected();
                }
            }
        };
    }

}
