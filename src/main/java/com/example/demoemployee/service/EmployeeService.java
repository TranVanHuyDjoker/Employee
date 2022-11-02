package com.example.demoemployee.service;

import com.example.demoemployee.controller.dto.request.EmployeeRequest;
import com.example.demoemployee.controller.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> getAllEmployee();

    void insertEmployee(EmployeeRequest request);

    void updateEmployee(EmployeeRequest request);

    void deleteEmployee(Long id);

    List<EmployeeResponse> searchEmployee(String keyword);
}
