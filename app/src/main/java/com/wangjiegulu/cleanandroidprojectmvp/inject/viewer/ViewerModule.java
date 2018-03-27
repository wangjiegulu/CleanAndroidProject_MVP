package com.wangjiegulu.cleanandroidprojectmvp.inject.viewer;

import android.content.Context;
import android.support.annotation.Nullable;

import com.wangjiegulu.cleanandroidprojectmvp.inject.scope.Scope_Viewer;
import com.wangjiegulu.mvparchitecture.library.viewer.Viewer;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wangjie on 10/19/16.
 */
@Module
@Scope_Viewer
public class ViewerModule {
    private WeakReference<Viewer> viewerRef;

    public ViewerModule(Viewer viewer) {
        this.viewerRef = new WeakReference<>(viewer);
    }

    @Provides
//    @Nullable
    public Viewer providerViewer() {
        return null == viewerRef ? null : viewerRef.get();
    }

    @Provides
    @Nullable
    public Context providerContext() {
        Viewer viewer;
        return null == viewerRef || null == (viewer = viewerRef.get()) ? null : viewer.context();
    }


}
