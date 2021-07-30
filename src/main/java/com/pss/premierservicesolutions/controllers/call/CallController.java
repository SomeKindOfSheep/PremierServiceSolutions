package com.pss.premierservicesolutions.controllers.call;

import com.pss.premierservicesolutions.entity.Call;
import com.pss.premierservicesolutions.entity.WorkRequest;
import com.pss.premierservicesolutions.exception.MessagingAPIException;
import com.pss.premierservicesolutions.exception.MessagingAPIExceptionMessage;
import com.pss.premierservicesolutions.exception.MessagingAPII18nMessageResolver;
import com.pss.premierservicesolutions.services.CallMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/call")
public class CallController {

   /*
    • Answer incoming call - done
    • Find client details - done see client maintenance controller
    • Log details of client problem - done see work request
    • View details of previous calls to/from client - done
    • Submit work request if appropriate - done
    • Add call to existing work request if appropriate - done
    • End call - done
    */

    @Autowired
    CallMapService callMapService;

    @Autowired
    MessagingAPII18nMessageResolver messagingAPII18nMessageResolver;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Optional<Call> getCallDetails(@PathVariable String id){
        if (StringUtils.isEmpty(id)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return callMapService.getCallDetails(Long.parseLong(id));
    }

    @PostMapping(path = "/submit/{employeeId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public CallDTO submitCall(@RequestBody Call call, @PathVariable String employeeId){
        if (StringUtils.isEmpty(employeeId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return callMapService.submitNotLinkedCall(call, Long.parseLong(employeeId));
    }

    @PutMapping(path = "/submitwork/{employeeId}/{workRequestId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public CallDTO submitWorkLinkedCall(@RequestBody Call call, @PathVariable String employeeId, @PathVariable String workRequestId){
        if (StringUtils.isEmpty(employeeId) || StringUtils.isEmpty(workRequestId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return callMapService.submitWorkLinkedCall(call, Long.parseLong(employeeId), Long.parseLong(workRequestId));
    }

    @PutMapping(path = "/submitclient/{employeeId}/{clientId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public CallDTO submitClientLinkedCall(@RequestBody Call call, @PathVariable String employeeId, @PathVariable String clientId){
        if (StringUtils.isEmpty(employeeId) || StringUtils.isEmpty(clientId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return callMapService.submitClientLinkedCall(call, Long.parseLong(employeeId), Long.parseLong(clientId));
    }

    @PutMapping(path = "/submitcomplaint/{employeeId}/{complaintId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public CallDTO submitComplaintLinkedCall(@RequestBody Call call, @PathVariable String employeeId, @PathVariable String complaintId){
        if (StringUtils.isEmpty(employeeId) || StringUtils.isEmpty(complaintId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return callMapService.submitComplaintLinkedCall(call, Long.parseLong(employeeId), Long.parseLong(complaintId));
    }

}
