package com.huawei.pojo.product;

import java.util.List;

public class ServiceCapability {
    private String service_id;
    private String service_type;
    private List<Properties> properties;
    private List<Commands> commands;
    private String description;
    private String option;

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public List<Properties> getProperties() {
        return properties;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
    }

    public List<Commands> getCommands() {
        return commands;
    }

    public void setCommands(List<Commands> commands) {
        this.commands = commands;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
