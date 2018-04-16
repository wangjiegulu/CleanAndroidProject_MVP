package com.wangjiegulu.capmvp.util;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 27/03/2018.
 */
public class TextUtilTest {
    @Test
    public void convertReadableCount() throws Exception {
        Assert.assertEquals("18", TextUtil.convertReadableCount(18));

        Assert.assertEquals("128", TextUtil.convertReadableCount(128));

        Assert.assertEquals("1.3k", TextUtil.convertReadableCount(1288));

        Assert.assertEquals("1.7k", TextUtil.convertReadableCount(1729));

        Assert.assertEquals("142.9k", TextUtil.convertReadableCount(142923));

    }

}