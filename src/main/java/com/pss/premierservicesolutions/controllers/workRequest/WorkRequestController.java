package com.pss.premierservicesolutions.controllers.workRequest;

import com.pss.premierservicesolutions.entity.State;
import com.pss.premierservicesolutions.entity.WorkRequest;
import com.pss.premierservicesolutions.entity.WorkRequestPriority;
import com.pss.premierservicesolutions.services.WorkRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.Optional;

@RestController
@RequestMapping("/workrequest")
public class WorkRequestController {

    /*
    • Receive service requests
    • Assign requests as jobs to maintenance technicians
    • Track jobs
    • Escalate jobs
    • Re-assign jobs when necessary
    • Close service requests*/

    @Autowired
    WorkRequestService workRequestService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public WorkRequestDTO viewWorkRequestById(@PathVariable String id){
        return workRequestService.viewWorkRequestById(Long.parseLong(id));
    }

    @PostMapping(path = "/{clientId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public WorkRequest addWorkRequest(@RequestBody WorkRequest workRequest, @PathVariable String clientId){
        return workRequestService.addWorkRequest(workRequest, Long.parseLong(clientId.trim()));
    }

    @PutMapping(path = "/addtechnician/{workRequestId}/{technicianId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public WorkRequest addTechniciansToRequest(@PathVariable String workRequestId, @PathVariable String technicianId){
        return workRequestService.addTechniciansToRequest(Long.parseLong(workRequestId.trim()), Long.parseLong(technicianId.trim()));
    }

    @PutMapping(path = "/changestate/{state}/{workrequestId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public WorkRequest changeState(@PathVariable State state, @PathVariable String workrequestId){
        return workRequestService.changeState(state, Long.parseLong(workrequestId.trim()));
    }

    @PutMapping(path = "/changepriority/{priority}/{workrequestId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public WorkRequest changePriority(@PathVariable WorkRequestPriority priority, @PathVariable String workrequestId){
        return workRequestService.changePriority(priority, Long.parseLong(workrequestId.trim()));
    }


}
