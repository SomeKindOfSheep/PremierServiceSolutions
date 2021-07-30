package com.pss.premierservicesolutions.controllers.sla;

import com.pss.premierservicesolutions.entity.ServiceLevelAgreement;
import com.pss.premierservicesolutions.exception.MessagingAPIException;
import com.pss.premierservicesolutions.exception.MessagingAPIExceptionMessage;
import com.pss.premierservicesolutions.exception.MessagingAPII18nMessageResolver;
import com.pss.premierservicesolutions.services.ServiceLevelAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sla")
public class SlaController {

    /*• Capture details of service agreements with client - done*/

    @Autowired
    ServiceLevelAgreementService slaService;

    @Autowired
    MessagingAPII18nMessageResolver messagingAPII18nMessageResolver;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON)
    public List<ServiceLevelAgreement> getAllSla(){
        return slaService.getAllSla();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Optional<ServiceLevelAgreement> getSlaById(@PathVariable String id){
        if (StringUtils.isEmpty(id)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return slaService.getSlaById(Long.parseLong(id));
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceLevelAgreement createSla(@RequestBody ServiceLevelAgreement sla){
        return slaService.createSla(sla);
    }

}
