package com.wangjiegulu.cleanandroidprojectmvp.provider.dal.net.http.webapi;


import com.wangjiegulu.cleanandroidprojectmvp.provider.bll.application.ProviderApplication;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 11/5/16.
 */
public final class WebApiConstants {
    private WebApiConstants() {
    }

    private static final String HOST_STUFF = "github.com";

    // http ITG (test)
    private static final String HOST_ITG = getITGHost();
    private static final String HTTP_URL_ITG = "http://" + HOST_ITG;
    private static final int HTTP_PORT_ITG = 80;
    private static final String HTTPS_URL_ITG = "https://" + HOST_ITG;
    private static final int HTTPS_PORT_ITG = 443;

    // http STG
    private static final String HOST_STG = "api." + HOST_STUFF;
    private static final String HTTP_URL_STG = "http://" + HOST_STG;
    private static final int HTTP_PORT_STG = 80;
    private static final String HTTPS_URL_STG = "https://" + HOST_STG;
    private static final int HTTPS_PORT_STG = 443;

//    @Discard(apply = DiscardApplyConstants.Publish._TRUE, srcCode = "{ return HOST_STG; }")
    public static String getITGHost() {
        return "api." + HOST_STUFF;
    }

    public static String getHttpHost() {
        return ProviderApplication.isProdEnv() ? HTTP_URL_STG + ":" + HTTP_PORT_STG : HTTP_URL_ITG + ":" + HTTP_PORT_ITG;
    }

    public static String getHttpsHost() {
        return ProviderApplication.isProdEnv() ? HTTPS_URL_STG + ":" + HTTPS_PORT_STG : HTTPS_URL_ITG + ":" + HTTPS_PORT_ITG;
    }

    public static String formatHttpWebApi(String httpWebApi) {
        String httpHost = getHttpHost();
        if (!httpWebApi.contains(httpHost)) {
            httpWebApi = httpHost + httpWebApi;
        }
        return httpWebApi;
    }

    public static String formatHttpsWebApi(String httpWebApi) {
        String httpsHost = getHttpsHost();
        if (!httpWebApi.contains(httpsHost)) {
            httpWebApi = httpsHost + httpWebApi;
        }
        return httpWebApi;
    }

    /**
     * is internal url
     */
//    public static boolean isInternalUrl(String url) {
//        if (null == url || url.startsWith("/")) {
//            return false;
//        }
//        Uri uri = Uri.parse(url);
//        String host = uri.getHost();
//        return !TextUtil.isEmpty(host) && host.contains(HOST_STUFF);
//    }
}
