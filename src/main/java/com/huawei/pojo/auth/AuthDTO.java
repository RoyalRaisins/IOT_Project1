package com.huawei.pojo.auth;

public class AuthDTO {

    private IdentityDTO identity;

    private ScopeDTO scope;

    public IdentityDTO getIdentity() {
        return identity;
    }

    public void setIdentity(IdentityDTO identity) {
        this.identity = identity;
    }

    public ScopeDTO getScope() {
        return scope;
    }

    public void setScope(ScopeDTO scope) {
        this.scope = scope;
    }
}
