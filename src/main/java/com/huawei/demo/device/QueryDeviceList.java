package com.huawei.demo.device;

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

import org.json.JSONObject;

public class QueryDeviceList {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException, URISyntaxException {
        String token = Authentication.getToken();

        String project_id = "ee566a8bab3f42feaa1c72b7f0136cbd";
        String device_id = "6388501a688437315b86d573_sensor";
        String url = Constants.DEVICE_COMMAND_URL;
        url = String.format(url, project_id,device_id);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json");
        header.put("X-Auth-Token", token);

        Map<String, String> params = new HashMap<String, String>();
        //params.put("gateway_id", "5e09f371334dd4f337056da0_gaoshang_001");
        //params.put("node_id", "gaoshang_001");

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.initClient();

        StreamClosedHttpResponse httpResponse = httpUtils.doGet(url, header, params);
        System.out.println(httpResponse.getStatusLine());
        System.out.println(httpResponse.getContent());
    }

    public void SendCommand(JSONObject body) throws NoSuchAlgorithmException, KeyManagementException, IOException, URISyntaxException{
        String token = Authentication.getToken();

        String project_id = "ee566a8bab3f42feaa1c72b7f0136cbd";
        String device_id = "6388501a688437315b86d573_sensor";
        String url = Constants.DEVICE_POST_COMMAND_URL;
        url = String.format(url, project_id,device_id);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json");
        header.put("X-Auth-Token", token);

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.initClient();
        System.out.println(body);
        StreamClosedHttpResponse httpResponse = httpUtils.doPost(url, header, body.toString());
        System.out.println("Response received");
        System.out.println(httpResponse.getStatusLine());
        System.out.println(httpResponse.getContent());
    }
}
