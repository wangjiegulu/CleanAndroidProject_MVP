package com.wangjiegulu.capmvp.inject.viewer;


import com.wangjiegulu.capmvp.inject.scope.Scope_Viewer;
import com.wangjiegulu.capmvp.inject.user.UserComponent;
import com.wangjiegulu.capmvp.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by wangjie on 10/19/16
 */
@Component(dependencies = UserComponent.class, modules = ViewerModule.class)
@Scope_Viewer
public interface ViewerComponent {

    void inject(MainActivity viewer);

}
