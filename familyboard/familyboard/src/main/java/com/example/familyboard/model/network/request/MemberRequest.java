package com.example.familyboard.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    private String name;

    private String auth;

    private String account;

    private String password;

    private String email;

}
