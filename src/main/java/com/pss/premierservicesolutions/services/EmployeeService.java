package com.pss.premierservicesolutions.services;

import com.pss.premierservicesolutions.entity.Employee;
import com.pss.premierservicesolutions.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllActiveEmployees(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(long id){
        return employeeRepository.findById(id);
    }

    public void createEmployee(Employee employee){
        employeeRepository.saveAndFlush(employee);
    }


}
