package com.pss.premierservicesolutions.controllers;

import com.pss.premierservicesolutions.entity.Call;
import com.pss.premierservicesolutions.services.CallMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Optional;

@RestController
@RequestMapping("/call")
public class CallController {

    @Autowired
    CallMapService callMapService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Optional<Call> getClientById(@PathVariable String id){
        return callMapService.getCallDetails(Long.parseLong(id));
    }

    @PostMapping(path = "/{employeeId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Call submitCall(@RequestBody Call call, @PathVariable String employeeId){
        return callMapService.submitCall(call, Long.parseLong(employeeId));
    }

}
