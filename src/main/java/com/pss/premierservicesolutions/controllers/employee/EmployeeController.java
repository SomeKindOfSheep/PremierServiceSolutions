package com.pss.premierservicesolutions.controllers.employee;

import com.pss.premierservicesolutions.entity.Employee;
import com.pss.premierservicesolutions.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON)
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Optional<Employee> getEmployeeById(@PathVariable String id){
        return employeeService.getEmployeeById(Long.parseLong(id));
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee){
       return employeeService.createEmployee(employee);
    }

    @DeleteMapping(path = "/{employeeId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Employee removeEmployee(@PathVariable long employeeId){
        return employeeService.removeEmployee(employeeId);
    }
    

}
