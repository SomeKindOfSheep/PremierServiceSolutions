package com.pss.premierservicesolutions.services;

import com.pss.premierservicesolutions.entity.Employee;
import com.pss.premierservicesolutions.entity.State;
import com.pss.premierservicesolutions.entity.WorkRequest;
import com.pss.premierservicesolutions.entity.WorkRequestPriority;
import com.pss.premierservicesolutions.repositories.ClientRepository;
import com.pss.premierservicesolutions.repositories.EmployeeRepository;
import com.pss.premierservicesolutions.repositories.WorkRequestRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkRequestService {

    @Autowired
    WorkRequestRepository workRequestRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ClientRepository clientRepository;

    public WorkRequest addWorkRequest(WorkRequest workRequest, long clientId) {

        workRequest.setClient(clientRepository.findById(clientId).get());

        return workRequestRepository.saveAndFlush(workRequest);
    }

    public Optional<WorkRequest> viewWorkRequestById(long workRequestId) {
        return workRequestRepository.findById(workRequestId);
    }

    public WorkRequest addTechniciansToRequest(long workRequestId, long technicianId) {
        WorkRequest workRequest = viewWorkRequestById(workRequestId).get();
        List<Employee> technicians = workRequest.getEmployee();
        technicians.add(employeeRepository.findById(technicianId).get());
        BeanUtils.copyProperties(workRequest, workRequest, "id");

        return workRequestRepository.saveAndFlush(workRequest);
    }

    // if you view the request, you'll see all the employees
    /*public List<Employee>  viewTechniciansOnRequest(long workRequestId){
        return null;
    }*/

    public WorkRequest changeState(State state, long workRequestId) {

        WorkRequest workRequest = viewWorkRequestById(workRequestId).get();
        workRequest.setState(state);
        BeanUtils.copyProperties(workRequest, workRequest, "id");
        return workRequestRepository.saveAndFlush(workRequest);
    }

    public WorkRequest changePriority(WorkRequestPriority priority, long workRequestId ) {
        WorkRequest workRequest = viewWorkRequestById(workRequestId).get();
        workRequest.setWorkRequestPriority(priority);
        BeanUtils.copyProperties(workRequest, workRequest, "id");
        return workRequestRepository.saveAndFlush(workRequest);
    }

    // part of the change state
    /*public void closeWorkRequest(long workRequestId) {
    }*/

}
