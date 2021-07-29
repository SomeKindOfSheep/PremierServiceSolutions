package com.pss.premierservicesolutions.controllers.clientSatisfaction;

import com.pss.premierservicesolutions.entity.Complaint;
import com.pss.premierservicesolutions.entity.FollowUp;
import com.pss.premierservicesolutions.entity.State;
import com.pss.premierservicesolutions.services.ClientSatisfactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Optional;

@RestController
@RequestMapping("/satisfaction")
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

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Optional<Complaint> getComplaintById(@PathVariable String id){
        return clientSatisfactionService.viewComplaint(Long.parseLong(id));
    }

    @PutMapping(path = "/complaint/{state}/{complaintId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Complaint updateComplainState( @PathVariable State state, @PathVariable String complaintId){
        return clientSatisfactionService.updateComplaintState(state, Long.parseLong(complaintId.trim()));
    }

    @PutMapping(path = "/followup/{state}/{followUpId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public FollowUp updateFollowUpState( @PathVariable State state, @PathVariable String followUpId){
        return clientSatisfactionService.updateFollowUpState(state, Long.parseLong(followUpId.trim()));
    }

    @PostMapping(path = "/{complaintId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Complaint addFollowUpToComplaint(@RequestBody FollowUp followUp, @PathVariable String complaintId){
        return clientSatisfactionService.addFollowUp(followUp, Long.parseLong(complaintId));
    }

    @PostMapping(path = "/addcomplaint/{clientId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public Complaint addComplaint(@RequestBody Complaint complaint, @PathVariable String clientId){
        return clientSatisfactionService.addComplaint(complaint, Long.parseLong(clientId));
    }

}
