package com.pss.premierservicesolutions.controllers.clientMaintenance;

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
    • Capture and maintain details for individual client - done
    • Capture and maintain details for business client - done
    • Capture management information about client - done

    */

    @Autowired
    ClientMaintenanceService clientMaintenanceService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public MaintenanceDTO getClientById(@PathVariable String id){
        return clientMaintenanceService.getClientById(Long.parseLong(id));
    }

    @PostMapping(path = "/{contractId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@RequestBody Client client, @PathVariable String contractId){
       return clientMaintenanceService.createClient(client, Long.parseLong(contractId));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceDTO updateClientDetails(@PathVariable String id, @RequestBody Client client){
        return clientMaintenanceService.updateClientDetails(Long.parseLong(id.trim()), client);
    }

}
