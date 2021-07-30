package com.pss.premierservicesolutions.controllers.employee;

import com.pss.premierservicesolutions.entity.Employee;
import com.pss.premierservicesolutions.exception.MessagingAPIException;
import com.pss.premierservicesolutions.exception.MessagingAPIExceptionMessage;
import com.pss.premierservicesolutions.exception.MessagingAPII18nMessageResolver;
import com.pss.premierservicesolutions.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    MessagingAPII18nMessageResolver messagingAPII18nMessageResolver;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON)
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Optional<Employee> getEmployeeById(@PathVariable String id){
        if (StringUtils.isEmpty(id)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
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
        if (employeeId < 0){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return employeeService.removeEmployee(employeeId);
    }
    

}
