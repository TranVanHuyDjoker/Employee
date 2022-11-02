package com.example.demoemployee.service.impl;

import com.example.demoemployee.controller.dto.request.EmployeeRequest;
import com.example.demoemployee.controller.dto.response.EmployeeResponse;
import com.example.demoemployee.entities.Employee;
import com.example.demoemployee.repository.EmployeeRepository;
import com.example.demoemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository repository;

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        return repository.findAll()
                .stream()
                .map(EmployeeResponse::new)// entiti ->respon
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
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
        if (Objects.nonNull(request.getId())) { //tồn tại !=null
            employee = repository.findById(request.getId()).orElseThrow(() -> new RuntimeException("loi")); //tìm employ theo id
        }
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setPassword(request.getPassword());

        return employee;
    }

    @Override
    @Transactional
    public void updateEmployee(EmployeeRequest request) {
        Employee employee = repository.findById(request.getId()).orElseThrow(() -> new RuntimeException("abc"));
        Set<String> emails = repository.findAll()
                .stream()
                .map(Employee::getEmail)
                .collect(Collectors.toSet());
        if (!Objects.equals(request.getEmail(), employee.getEmail()) && emails.contains(request.getEmail())) {
            throw new RuntimeException("Email da ton tai");
        }
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setPassword(request.getPassword());
        repository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        boolean flag = repository.existsEmployeeById(id);
        if (!flag) {
            throw new RuntimeException("khong ton tai");
        }
        repository.deleteById(id);
    }

    @Override
    public List<EmployeeResponse> searchEmployee(String keyword) {
        return repository.findEmployee(keyword);
    }
}
