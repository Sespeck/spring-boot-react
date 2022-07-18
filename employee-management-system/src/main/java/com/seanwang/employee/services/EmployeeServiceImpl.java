package com.seanwang.employee.services;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.seanwang.employee.entity.EmployeeEntity;
import com.seanwang.employee.model.Employee;
import com.seanwang.employee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(emp -> new Employee(
                        emp.getId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmailId()))
                .toList();
    }

    @Override
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public Employee getEmployeeById(long id) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRepository.findById(id).get(),employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(long id, Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeEntity.setEmailId(employee.getEmailId());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeRepository.save(employeeEntity);
        return employee;
    }
}
