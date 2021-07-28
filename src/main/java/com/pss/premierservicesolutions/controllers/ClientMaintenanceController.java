package com.pss.premierservicesolutions.controllers;

import com.pss.premierservicesolutions.entity.Client;
import com.pss.premierservicesolutions.services.ClientMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Optional;


@RestController
@RequestMapping("/client")
public class ClientMaintenanceController {

    @Autowired
    ClientMaintenanceService clientMaintenanceService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Optional<Client> getClientById(@PathVariable String id){
        return clientMaintenanceService.getClientById(Long.parseLong(id));
    }

}
