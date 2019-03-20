package com.wangjiegulu.capmvp.ui.main;

import com.wangjiegulu.capmvp.provider.bll.interactor.bo.GithubRepoBO;
import com.wangjiegulu.capmvp.util.TextUtil;
import com.wangjiegulu.rapidooo.api.OOO;
import com.wangjiegulu.rapidooo.api.OOOConversion;
import com.wangjiegulu.rapidooo.api.OOOs;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 16/04/2018.
 */
@OOOs(suffix = "MainVO", fromSuffix = "BO", ooos = {
        @OOO(from = GithubRepoBO.class, conversion = {
                @OOOConversion(
                        fieldName = "stargazersCount",
                        targetFieldName = "readableStargazersCount",
                        targetType = String.class,
                        conversionMethodName = "conversionReadableStargazersCount",
                        replace = false
                )
        })
})
public class MainVOGenerator {

    public static String conversionReadableStargazersCount(Integer stargazersCount) {
        return null == stargazersCount ? "unknown" : TextUtil.convertReadableCount(stargazersCount);
    }

}
