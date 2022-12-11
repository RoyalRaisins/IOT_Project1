package com.huawei.demo.device;

import com.huawei.demo.apig.SignUtil;
import com.huawei.util.Constants;
import com.huawei.util.HttpUtils;
import com.huawei.util.StreamClosedHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class QueryDeviceListByAK {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
        String project_id = "23123";
        String url = Constants.DEVICE_COMMAND_URL;
        url = String.format(url, project_id);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json");

        Map<String, String> params = new HashMap<String, String>();
        params.put("node_id", "gaoshang_001");

        HttpRequestBase httpRequestBase = SignUtil.signRequest(url, "GET", header, null, params);

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.initClient();

        StreamClosedHttpResponse httpResponse = (StreamClosedHttpResponse)httpUtils.execute(httpRequestBase);
        System.out.println(httpResponse.getStatusLine());
        System.out.println(httpResponse.getContent());
    }
}
