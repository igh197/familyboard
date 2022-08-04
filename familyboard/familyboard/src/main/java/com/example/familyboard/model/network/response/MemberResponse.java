package com.example.familyboard.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
    private String name;

    private String auth;

    private String account;

    private String password;

    private String email;

}
