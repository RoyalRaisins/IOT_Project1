package com.huawei.pojo.auth;

import java.util.List;

public class IdentityDTO {

    private List<String> methods;

    private PasswordDTO password;

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public PasswordDTO getPassword() {
        return password;
    }

    public void setPassword(PasswordDTO password) {
        this.password = password;
    }
}
