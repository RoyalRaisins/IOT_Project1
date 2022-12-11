package com.huawei.pojo.product;

import java.util.List;

public class Commands {
    private String command_name;
    private List<CommandParam> paras;
    private List<Response> responses;

    public String getCommand_name() {
        return command_name;
    }

    public void setCommand_name(String command_name) {
        this.command_name = command_name;
    }

    public List<CommandParam> getParas() {
        return paras;
    }

    public void setParas(List<CommandParam> paras) {
        this.paras = paras;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}
