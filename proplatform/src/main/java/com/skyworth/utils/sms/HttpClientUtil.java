package com.skyworth.utils.sms;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Shallow on 2018/4/23.
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static PoolingHttpClientConnectionManager phcm = null;

    public static String postJson(String url, String body, String charset){
        String result = null;

        if (charset == null){
            charset = "UTF-8";
        }

        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;

        try {
            httpClient = HttpClientUtil.getHttpClient();
            httpPost = new HttpPost(url);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(10000)
                    .setSocketTimeout(10000)
                    .build();
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type","application/json;charset=utf-8");

            StringEntity stringEntity = new StringEntity(body,"UTF-8");
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);

            if (response != null){
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch (Exception e){
            logger.error("Http post request failed", e);
        }

        return  result;
    }

    private static CloseableHttpClient getHttpClient(){
        LayeredConnectionSocketFactory sslsf = null;

        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            logger.error("SSLConnectionSocketFactory building error",e);
        }

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http",sslsf).register("https",new PlainConnectionSocketFactory()).build();

        phcm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        phcm.setDefaultMaxPerRoute(20);
        phcm.setMaxTotal(200);

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(phcm).build();
        return httpClient;
    }

}
