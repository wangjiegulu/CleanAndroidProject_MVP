package com.wangjiegulu.capmvp.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import com.wangjiegulu.capmvp.application.CAPApplication;
import com.wangjiegulu.capmvp.inject.viewer.DaggerViewerComponent;
import com.wangjiegulu.capmvp.inject.viewer.ViewerComponent;
import com.wangjiegulu.capmvp.inject.viewer.ViewerModule;
import com.wangjiegulu.capmvp.usagesupport.usage.XFunc0R;
import com.wangjiegulu.mvparchitecture.library.contract.OnViewerDestroyListener;
import com.wangjiegulu.mvparchitecture.library.contract.OnViewerLifecycleListener;
import com.wangjiegulu.mvparchitecture.library.viewer.Viewer;

//import com.wangjiegulu.capmvp.inject.viewer.DaggerViewerComponent;
//import com.wangjiegulu.capmvp.provider.support.bridge.usage.XFunc0R;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 23/03/2018.
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements Viewer {
    private BaseViewerDelegate viewerDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewerDelegate = new BaseViewerDelegate(this);
    }

    /* Inject */
    private XFunc0R<ViewerComponent> createViewerComponent = () -> DaggerViewerComponent.builder()
            .userComponent(CAPApplication.instance.userComponent)
            .viewerModule(new ViewerModule(this))
            .build();

    @VisibleForTesting
    public void setCreateViewerComponent(XFunc0R<ViewerComponent> createViewerComponent) {
        this.createViewerComponent = createViewerComponent;
    }

    protected ViewerComponent getViewerComponent() {
        return createViewerComponent.call();
    }
    /* Inject */

    @Override
    protected void onResume() {
        super.onResume();
        viewerDelegate.onViewerResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewerDelegate.onViewerPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewerDelegate.onViewerDestroy();
    }

    @Override
    public Viewer bind(OnViewerLifecycleListener onViewerLifecycleListener) {
        return viewerDelegate.bind(onViewerLifecycleListener);
    }

    @Override
    public Viewer bind(OnViewerDestroyListener onViewerDestroyListener) {
        return viewerDelegate.bind(onViewerDestroyListener);
    }

    @Nullable
    @Override
    public Context context() {
        return viewerDelegate.context();
    }

    @Override
    public void showToast(String message) {
        viewerDelegate.showToast(message);
    }

    @Override
    public void showToast(int resStringId) {
        viewerDelegate.showToast(resStringId);
    }

    @Override
    public void showLoadingDialog(String message) {
        viewerDelegate.showLoadingDialog(message);
    }

    @Override
    public void showLoadingDialog(int resStringId) {
        viewerDelegate.showLoadingDialog(resStringId);
    }

    @Override
    public void cancelLoadingDialog() {
        viewerDelegate.cancelLoadingDialog();
    }
}
