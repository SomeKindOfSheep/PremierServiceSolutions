package com.pss.premierservicesolutions.controllers;

import com.pss.premierservicesolutions.entity.Client;
import com.pss.premierservicesolutions.services.ClientMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Optional;


@RestController
@RequestMapping("/client")
public class ClientMaintenanceController {

    /*
    • Capture and maintain details for individual client
    • Capture and maintain details for business client
    • Capture details of service agreements with client
    • Capture management information about client*/

    @Autowired
    ClientMaintenanceService clientMaintenanceService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Optional<Client> getClientById(@PathVariable String id){
        return clientMaintenanceService.getClientById(Long.parseLong(id));
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@RequestBody Client client){
       return clientMaintenanceService.addClient(client);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Client updateClientDetails(@PathVariable String id, @RequestBody Client client){
        return clientMaintenanceService.updateClientDetails(Long.parseLong(id.trim()), client);
    }

}
