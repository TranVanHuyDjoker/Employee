package com.example.demoemployee.service.impl;

import com.example.demoemployee.controller.dto.request.EmployeeRequest;
import com.example.demoemployee.controller.dto.response.EmployeeResponse;
import com.example.demoemployee.entities.Employee;
import com.example.demoemployee.repository.EmployeeRepository;
import com.example.demoemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository repository;

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        return repository.findAll()
                .stream()
                .map(EmployeeResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public void insertEmployee(EmployeeRequest request) {
        boolean exits = repository.existsEmployeeByEmail(request.getEmail());
        if (exits) {
            throw new RuntimeException("Email da ton tai");
        }
        Employee employee = fromDto(request);
        repository.save(employee);
    }

    private Employee fromDto(EmployeeRequest request) {
        Employee employee = new Employee();
        if (Objects.nonNull(request.getId())) {
            employee = repository.findById(request.getId()).orElseThrow(() -> new RuntimeException("loi"));
        }
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setPassword(request.getPassword());
        return employee;
    }

    @Override
    public void deleteEmployee(Long id) {
        boolean flag = repository.existsEmployeeById(id);
       if(!flag){
           throw new RuntimeException("khong ton tai");
       }
       repository.deleteById(id);
    }

    @Override
    public List<EmployeeResponse> searchEmployee(String keyword) {
        return repository.findEmployee(keyword);
    }
}
