package com.huawei.demo.device;

import com.huawei.demo.auth.Authentication;
import com.huawei.pojo.device.AddDevice;
import com.huawei.pojo.device.AuthInfo;
import com.huawei.util.Constants;
import com.huawei.util.HttpUtils;
import com.huawei.util.JsonUtils;
import com.huawei.util.StreamClosedHttpResponse;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class CreateDevice {
    public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, IOException {
        String token = Authentication.getToken();
        AddDevice addDevice = new AddDevice();
        AuthInfo authInfo = new AuthInfo();

        authInfo.setAuth_type("SECRET");
        authInfo.setSecret("123456678");
        authInfo.setSecure_access(true);
        authInfo.setTimeout(300);

        addDevice.setAuth_info(authInfo);
        addDevice.setDescription("test device");
        addDevice.setDevice_name("test_deviceName2");
        addDevice.setNode_id("1111222223333444");
        addDevice.setProduct_id("5e09f371334dd4f337056da0");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Auth-Token", token);

        String project_id = "11111";
        String url = Constants.DEVICE_COMMAND_URL;
        url = String.format(url, project_id);
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.initClient();
        StreamClosedHttpResponse httpResponse = httpUtils.doPost(url, headers, JsonUtils.Obj2String(addDevice));

        System.out.println(httpResponse.getContent());

    }
}
