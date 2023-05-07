package com.omerfarukozcan.model;

import lombok.Data;

@Data
public class SignInRequest {

    private String email;
    private String password;

}
