package com.huawei.demo.product;

import com.huawei.demo.auth.Authentication;
import com.huawei.util.Constants;
import com.huawei.util.HttpUtils;
import com.huawei.util.StreamClosedHttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class QueryProductList {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException, URISyntaxException {
        String token = Authentication.getToken();

        String url = Constants.PRODUCT_COMMAND_URL;
        String project_id = "31231";
        url = String.format(url, project_id);

        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json");
        header.put("X-Auth-Token", token);

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.initClient();
        StreamClosedHttpResponse httpResponse = httpUtils.doGet(url, header, null);


        System.out.println(httpResponse.getStatusLine());
        System.out.println(httpResponse.getContent());

    }
}
