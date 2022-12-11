package com.huawei.util;

public class Constants {

    public static final String TOKEN_BASE_URL = "https://iam.cn-north-4.myhuaweicloud.com";
    public static final String IOTDM_BASE_URL = "https://iotda.cn-north-4.myhuaweicloud.com";

    public static final String TOKEN_ACCESS_URL = TOKEN_BASE_URL + "/v3/auth/tokens";

    public static final String DEVICE_COMMAND_URL = IOTDM_BASE_URL + "/v5/iot/%s/devices/%s";
    public static final String DEVICE_POST_COMMAND_URL = IOTDM_BASE_URL + "/v5/iot/%s/devices/%s/commands";
    public static final String PRODUCT_COMMAND_URL = IOTDM_BASE_URL + "/v5/iot/%s/products";
}
