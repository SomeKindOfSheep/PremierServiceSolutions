package com.pss.premierservicesolutions.controllers.sla;

import com.pss.premierservicesolutions.entity.ServiceLevelAgreement;
import com.pss.premierservicesolutions.services.ServiceLevelAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sla")
public class SlaController {

    /*• Capture details of service agreements with client - done*/

    @Autowired
    ServiceLevelAgreementService slaService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON)
    public List<ServiceLevelAgreement> getAllSla(){
        return slaService.getAllSla();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Optional<ServiceLevelAgreement> getSlaById(@PathVariable String id){
        return slaService.getSlaById(Long.parseLong(id));
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody ServiceLevelAgreement sla){
        slaService.createSla(sla);
    }

}
