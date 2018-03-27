package com.wangjiegulu.cleanandroidprojectmvp.inject.viewer;


import com.wangjiegulu.cleanandroidprojectmvp.inject.scope.Scope_Viewer;
import com.wangjiegulu.cleanandroidprojectmvp.inject.user.UserComponent;
import com.wangjiegulu.cleanandroidprojectmvp.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by wangjie on 10/19/16
 */
@Component(dependencies = UserComponent.class, modules = ViewerModule.class)
@Scope_Viewer
public interface ViewerComponent {

    void inject(MainActivity viewer);

}
