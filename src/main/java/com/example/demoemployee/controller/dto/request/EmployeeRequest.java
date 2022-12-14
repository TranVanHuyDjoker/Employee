package com.example.demoemployee.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeRequest {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("email")
    @Email
    private String email;

    @NotNull
    @JsonProperty("password")
    private String password;

    @NotNull
    @JsonProperty("phone")
    private String phone;

}
