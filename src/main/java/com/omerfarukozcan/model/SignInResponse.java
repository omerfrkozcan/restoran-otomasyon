package com.omerfarukozcan.model;

import lombok.Data;

@Data
public class SignInResponse {

    private boolean success;

    public SignInResponse() {
    }

    public SignInResponse(boolean success) {
        setSuccess(success);
    }

}
