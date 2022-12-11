package com.huawei.pojo.product;

import java.util.List;

public class ResponseParam {
    private String para_name;
    private String data_type;
    private boolean required;
    private List<String> enum_list;
    private int min;
    private int max;
    private int max_length;
    private double step;
    private String unit;
    private String description;

    public String getPara_name() {
        return para_name;
    }

    public void setPara_name(String para_name) {
        this.para_name = para_name;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public List<String> getEnum_list() {
        return enum_list;
    }

    public void setEnum_list(List<String> enum_list) {
        this.enum_list = enum_list;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax_length() {
        return max_length;
    }

    public void setMax_length(int max_length) {
        this.max_length = max_length;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
