package com.pss.premierservicesolutions.services;

import com.pss.premierservicesolutions.entity.Employee;
import com.pss.premierservicesolutions.repositories.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EmployeeService {
    public final String hash = "35454B055CC325EA1AF2126E27707052";

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(long id){
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee){
        String hashPassword = employee.getPassword();

        String md5Hex = DigestUtils.
                md5DigestAsHex((hashPassword+hash).getBytes(StandardCharsets.UTF_8)).
                toUpperCase(Locale.ROOT);
        employee.setPassword(md5Hex);

        return employeeRepository.saveAndFlush(employee);
    }

    public Employee removeEmployee(long employeeId){
        Employee employee = getEmployeeById(employeeId).get();
        employee.setDateRemoved(LocalDate.now());
        BeanUtils.copyProperties(employee,employee,"id");
        return employeeRepository.saveAndFlush(employee);
    }


}
