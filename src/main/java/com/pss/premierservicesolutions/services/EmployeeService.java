package com.pss.premierservicesolutions.services;

import com.pss.premierservicesolutions.entity.Employee;
import com.pss.premierservicesolutions.repositories.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(long id){
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee){
        return employeeRepository.saveAndFlush(employee);
    }

    public Employee removeEmployee(long employeeId){
        Employee employee = getEmployeeById(employeeId).get();
        employee.setDateRemoved(LocalDate.now());
        BeanUtils.copyProperties(employee,employee,"id");
        return employeeRepository.saveAndFlush(employee);
    }


}
