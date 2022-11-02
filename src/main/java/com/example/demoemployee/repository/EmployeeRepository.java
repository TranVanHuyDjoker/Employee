package com.example.demoemployee.repository;

import com.example.demoemployee.controller.dto.response.EmployeeResponse;
import com.example.demoemployee.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
    boolean existsEmployeeByEmail(String email);

    boolean existsEmployeeById(Long id);

    @Query(value = "select s from Employee s " +
            "where s.email like concat('%', ?1, '%') or  s.phone like concat('%', ?1, '%')")
    List<EmployeeResponse> findEmployee(String keyword);

//    @Query(value = "SELECT email FROM emp" , nativeQuery = true)
//    List<EmployeeResponse> getEmployeeByEmail();



}
