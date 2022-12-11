package com.huawei.pojo.device;

public class AuthInfo {
    private String auth_type;
    private String secret;
    private String fingerprint;
    private boolean secure_access;
    private Integer timeout;

    public String getAuth_type() {
        return auth_type;
    }

    public void setAuth_type(String auth_type) {
        this.auth_type = auth_type;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public boolean isSecure_access() {
        return secure_access;
    }

    public void setSecure_access(boolean secure_access) {
        this.secure_access = secure_access;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
