package com.huawei.pojo.product;

import java.util.List;

public class Response {
    private List<ResponseParam> paras;
    private String response_name;

    public List<ResponseParam> getParas() {
        return paras;
    }

    public void setParas(List<ResponseParam> paras) {
        this.paras = paras;
    }

    public String getResponse_name() {
        return response_name;
    }

    public void setResponse_name(String response_name) {
        this.response_name = response_name;
    }
}
