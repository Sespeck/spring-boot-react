package com.seanwang.employee.services;

import com.seanwang.employee.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    boolean deleteEmployee(Long id);

    Employee getEmployeeById(long id);

    Employee updateEmployee(long id, Employee employee);
}
