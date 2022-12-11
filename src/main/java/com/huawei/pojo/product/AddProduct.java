package com.huawei.pojo.product;

import java.util.List;

public class AddProduct {
     private String name;
     private String device_type;
     private String protocol_type;
     private String data_format;
     private String manufacturer_name;
     private String industry;
     private String description;
     private List<ServiceCapability> service_capabilities;
     private String appId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getProtocol_type() {
        return protocol_type;
    }

    public void setProtocol_type(String protocol_type) {
        this.protocol_type = protocol_type;
    }

    public String getData_format() {
        return data_format;
    }

    public void setData_format(String data_format) {
        this.data_format = data_format;
    }

    public String getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ServiceCapability> getService_capabilities() {
        return service_capabilities;
    }

    public void setService_capabilities(List<ServiceCapability> service_capabilities) {
        this.service_capabilities = service_capabilities;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
