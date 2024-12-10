package com.heroiclabs.mezon.model;

import lombok.Data;

@Data
public class ApiAuthenticateRequest {
    public ApiAuthenticateRequest(ApiAccountApp account){
        this.account = account;
    }
    // The App account details.
    private ApiAccountApp account;
}
