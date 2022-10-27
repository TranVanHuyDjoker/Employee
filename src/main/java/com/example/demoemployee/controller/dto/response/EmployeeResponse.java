package com.example.demoemployee.controller.dto.response;

import com.example.demoemployee.entities.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("email")
    private String ema;

    @JsonProperty("phone")
    private String phone;

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.ema = employee.getEmail();
        this.phone = employee.getPhone();
    }
}
