package com.pss.premierservicesolutions.controllers;

import com.pss.premierservicesolutions.entity.Contract;
import com.pss.premierservicesolutions.entity.ContractType;
import com.pss.premierservicesolutions.services.ContractMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Optional;

@RestController
@RequestMapping("/contract")
public class ContractMaintenanceController {

    @Autowired
    ContractMaintenanceService contractMaintenanceService;

    @GetMapping(path = "/{contractTypeId}/type", produces = MediaType.APPLICATION_JSON)
    public Optional<ContractType> getContractType(@PathVariable String contractTypeId){
        return contractMaintenanceService.getContractType(Long.parseLong(contractTypeId));
    }

    @PostMapping(path = "/type", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public ContractType addContractType(@RequestBody ContractType contractType){
        return contractMaintenanceService.addContractType(contractType);
    }

    @GetMapping(path = "/{contractId}", produces = MediaType.APPLICATION_JSON)
    public Optional<Contract> viewContract(@PathVariable String contractId){
        return contractMaintenanceService.viewContract(Long.parseLong(contractId));
    }


    @PostMapping(path = "/{contractTypeId}/{employeeId}/{slaId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Contract addContract(@RequestBody Contract contract,
                                    @PathVariable String contractTypeId,
                                    @PathVariable String employeeId,
                                    @PathVariable String slaId ){
        return contractMaintenanceService.createContract(contract, Long.parseLong(contractTypeId),Long.parseLong(employeeId),Long.parseLong(slaId));
    }


}
