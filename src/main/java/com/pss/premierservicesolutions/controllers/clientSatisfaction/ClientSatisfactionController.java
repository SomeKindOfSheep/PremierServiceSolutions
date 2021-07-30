package com.pss.premierservicesolutions.controllers.clientSatisfaction;

import com.pss.premierservicesolutions.entity.Complaint;
import com.pss.premierservicesolutions.entity.FollowUp;
import com.pss.premierservicesolutions.entity.State;
import com.pss.premierservicesolutions.exception.MessagingAPIException;
import com.pss.premierservicesolutions.exception.MessagingAPIExceptionMessage;
import com.pss.premierservicesolutions.exception.MessagingAPII18nMessageResolver;
import com.pss.premierservicesolutions.entity.exception.ResourceNotFoundException;
import com.pss.premierservicesolutions.services.ClientSatisfactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@RestController
@RequestMapping("/satisfaction")
@Transactional
public class ClientSatisfactionController {

        /*
        • Receive reports of events that may require client contact (may be a follow-up)
        • Initiate outgoing calls - done see call
        • Receive incoming calls - done see call
        • Capture contents and outcome of a call - done see call
        • Create a follow-up action if required - done
        • End call - done
        */

    @Autowired
    ClientSatisfactionService clientSatisfactionService;

    @Autowired
    MessagingAPII18nMessageResolver messagingAPII18nMessageResolver;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Optional<Complaint> getComplaintById(@PathVariable String id){
        if (StringUtils.isEmpty(id)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return clientSatisfactionService.viewComplaint(Long.parseLong(id));
    }

    @PutMapping(path = "/complaint/{state}/{complaintId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Complaint updateComplainState( @PathVariable State state, @PathVariable String complaintId) throws ResourceNotFoundException {

        if (StringUtils.isEmpty(complaintId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }

        return clientSatisfactionService.updateComplaintState(state, Long.parseLong(complaintId.trim()));
    }

    @PutMapping(path = "/followup/{state}/{followUpId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public FollowUp updateFollowUpState( @PathVariable State state, @PathVariable String followUpId) throws ResourceNotFoundException {

        if (StringUtils.isEmpty(followUpId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }

        return clientSatisfactionService.updateFollowUpState(state, Long.parseLong(followUpId.trim()));
    }

    @PostMapping(path = "/{complaintId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Complaint addFollowUpToComplaint(@RequestBody FollowUp followUp, @PathVariable String complaintId) throws ResourceNotFoundException {
        if (StringUtils.isEmpty(complaintId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return clientSatisfactionService.addFollowUp(followUp, Long.parseLong(complaintId));
    }

    @PostMapping(path = "/addcomplaint/{clientId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Complaint addComplaint(@RequestBody Complaint complaint, @PathVariable String clientId) throws ResourceNotFoundException {

        if (StringUtils.isEmpty(clientId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return clientSatisfactionService.addComplaint(complaint, Long.parseLong(clientId));
    }

}
