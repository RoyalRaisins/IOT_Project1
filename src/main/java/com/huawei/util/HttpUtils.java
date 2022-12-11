package com.huawei.util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static final String CONTENT_LENGTH = "Content-Length";
    private static CloseableHttpClient httpClient;

    public void initClient() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sslcontext = getSSLContent();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext, NoopHostnameVerifier.INSTANCE))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        RequestConfig.Builder builder = RequestConfig.custom();
        RequestConfig requestConfig = builder.build();
        httpClient = HttpClients.custom().setConnectionManager(connManager).setDefaultRequestConfig(requestConfig).build();
    }

    public SSLContext getSSLContent() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("TLSv1.2");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }

    public StreamClosedHttpResponse doPost(String url, Map<String, String> headerMap, String body) {
        HttpPost httpPost = new HttpPost(url);
        addHeader(headerMap, httpPost);
        httpPost.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        return (StreamClosedHttpResponse)execute(httpPost);

    }

    public StreamClosedHttpResponse doGet(String url, Map<String, String> headerMap, Map<String, String> params) throws URISyntaxException {
        HttpGet httpGet = new HttpGet(url);
        addHeader(headerMap, httpGet);
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(url);
        } catch (URISyntaxException e) {
            System.out.println(e);
        }
        if (params != null && !params.isEmpty()) {
            uriBuilder.setParameters(convertParams(params));
        }
        httpGet.setURI(uriBuilder.build());
        return (StreamClosedHttpResponse)execute(httpGet);

    }

    public String constructUri(String url, Map<String, String> params) throws URISyntaxException {
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(url);
        } catch (URISyntaxException e) {
            System.out.println(e);
        }
        if (params != null && !params.isEmpty()) {
            uriBuilder.setParameters(convertParams(params));
        }
        return uriBuilder.build().toString();

    }

    public List<NameValuePair> convertParams(Map<String, String> params) {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String paramKey : params.keySet()) {
            list.add(new BasicNameValuePair(paramKey, params.get(paramKey)));
        }
        return list;
    }


    private void addHeader(Map<String, String> headerMap, HttpUriRequest request) {
        if (headerMap == null) {
            return;
        }

        for (String headerName : headerMap.keySet()) {
            if (CONTENT_LENGTH.equalsIgnoreCase(headerName)) {
                continue;
            }
            String headValue = headerMap.get(headerName);
            request.addHeader(headerName, headValue);
        }
    }

    public HttpResponse execute(HttpUriRequest request) {

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                return new StreamClosedHttpResponse(response);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return null;
    }

}
