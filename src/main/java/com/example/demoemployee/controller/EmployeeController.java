package com.example.demoemployee.controller;

import com.example.demoemployee.controller.dto.request.EmployeeRequest;
import com.example.demoemployee.controller.dto.response.EmployeeResponse;
import com.example.demoemployee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/allEmployee")
    ResponseEntity<?> getAllEmployee(){
        List<EmployeeResponse> employeeResponses = employeeService.getAllEmployee();
        return  ResponseEntity.ok(employeeResponses);
    }

    @PostMapping("/insert")
    ResponseEntity<?> insertEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        employeeService.insertEmployee(employeeRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        employeeService.updateEmployee(employeeRequest );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    ResponseEntity<?> searchEmployee(@RequestParam String keyword){
        List<EmployeeResponse> e = employeeService.searchEmployee(keyword);
        return ResponseEntity.ok(e);

    }
}
