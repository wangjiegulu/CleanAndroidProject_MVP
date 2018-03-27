package com.wangjiegulu.cleanandroidprojectmvp.ui.main.vm;

import android.support.annotation.NonNull;

import com.wangjiegulu.cleanandroidprojectmvp.R;
import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.vm.VM;
import com.wangjiegulu.cleanandroidprojectmvp.provider.dal.net.http.entity.GithubRepository;
import com.wangjiegulu.cleanandroidprojectmvp.util.ResUtil;
import com.wangjiegulu.cleanandroidprojectmvp.util.TextUtil;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 23/03/2018.
 */
public class MainRepositoryVM extends VM<GithubRepository> {

    public MainRepositoryVM(@NonNull GithubRepository model) {
        super(model);
    }

    private String readableStargazersCount;

    public String getReadableStargazersCount() {
        if (null == readableStargazersCount) {
            Integer stargazersCount = getModel().getStargazersCount();
            readableStargazersCount = null == stargazersCount ?
                    ResUtil.getString(R.string.unknown_stargazers_count) : TextUtil.convertReadableCount(stargazersCount);
        }
        return readableStargazersCount;
    }
}
