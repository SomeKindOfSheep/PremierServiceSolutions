package com.pss.premierservicesolutions.controllers.contractMaintenance;

import com.pss.premierservicesolutions.entity.Contract;
import com.pss.premierservicesolutions.entity.ContractType;
import com.pss.premierservicesolutions.entity.exception.ResourceNotFoundException;
import com.pss.premierservicesolutions.exception.MessagingAPIException;
import com.pss.premierservicesolutions.exception.MessagingAPIExceptionMessage;
import com.pss.premierservicesolutions.exception.MessagingAPII18nMessageResolver;
import com.pss.premierservicesolutions.services.ContractMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.ws.rs.core.MediaType;
import java.util.Optional;

@RestController
@RequestMapping("/contract")
public class ContractMaintenanceController {

    /*
    • Define services offered - done
    • Define service levels - done
    • Define contract types as packages of services and service level agreements - not in my architecture
    • Manage availability of contract types - done
    • View performance of contract types - TODO
    • View customer agreement(s) - done
    */

    @Autowired
    ContractMaintenanceService contractMaintenanceService;

    @Autowired
    MessagingAPII18nMessageResolver messagingAPII18nMessageResolver;

    @GetMapping(path = "/{contractTypeId}/type", produces = MediaType.APPLICATION_JSON)
    public Optional<ContractType> getContractType(@PathVariable String contractTypeId){
        if (StringUtils.isEmpty(contractTypeId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return contractMaintenanceService.getContractType(Long.parseLong(contractTypeId));
    }

    @PostMapping(path = "/type", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public ContractType addContractType(@RequestBody ContractType contractType){
        return contractMaintenanceService.addContractType(contractType);
    }

    @GetMapping(path = "/{contractId}", produces = MediaType.APPLICATION_JSON)
    public Optional<Contract> viewContract(@PathVariable String contractId){
        if (StringUtils.isEmpty(contractId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return contractMaintenanceService.viewContract(Long.parseLong(contractId));
    }


    @PostMapping(path = "/{contractTypeId}/{employeeId}/{slaId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Contract addContract(@RequestBody Contract contract,
                                    @PathVariable String contractTypeId,
                                    @PathVariable String employeeId,
                                    @PathVariable String slaId ) throws ResourceNotFoundException {
        if (StringUtils.isEmpty(contractTypeId) || StringUtils.isEmpty(employeeId) || StringUtils.isEmpty(slaId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return contractMaintenanceService.createContract(contract, Long.parseLong(contractTypeId),Long.parseLong(employeeId),Long.parseLong(slaId));
    }

    @DeleteMapping(path = "/{contractId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public Contract removeContract(@PathVariable String contractId) throws ResourceNotFoundException {
        if (StringUtils.isEmpty(contractId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return contractMaintenanceService.removeContract(Long.parseLong(contractId));
    }

    @PutMapping(path = "/{contractId}/{slaId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public Contract linkSlaToContract(@PathVariable String contractId, @PathVariable String slaId) throws ResourceNotFoundException {
        if (StringUtils.isEmpty(contractId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return contractMaintenanceService.addSlaToContract(Long.parseLong(contractId),Long.parseLong(slaId));
    }

}
