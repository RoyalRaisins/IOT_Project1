package com.huawei.demo.product;

import com.huawei.demo.apig.SignUtil;
import com.huawei.util.Constants;
import com.huawei.util.HttpUtils;
import com.huawei.util.StreamClosedHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class QueryProductListByAK {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
        String url = Constants.PRODUCT_COMMAND_URL;
        String project_id = "31231";
        url = String.format(url, project_id);

        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json");

        HttpRequestBase httpRequestBase = SignUtil.signRequest(url, "GET", header, null, null);

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.initClient();
        StreamClosedHttpResponse httpResponse = (StreamClosedHttpResponse)httpUtils.execute(httpRequestBase);

        System.out.println(httpResponse.getStatusLine());
        System.out.println(httpResponse.getContent());
    }
}
