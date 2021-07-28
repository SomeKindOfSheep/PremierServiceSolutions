package com.pss.premierservicesolutions.services;

import com.pss.premierservicesolutions.entity.Employee;
import com.pss.premierservicesolutions.entity.State;
import com.pss.premierservicesolutions.entity.WorkRequest;
import com.pss.premierservicesolutions.repositories.EmployeeRepository;
import com.pss.premierservicesolutions.repositories.WorkRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkRequestService {

    @Autowired
    WorkRequestRepository workRequestRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    public void addWorkRequestService(WorkRequest workRequest){
    }

    public void submitWorkRequest(WorkRequest workRequest){

    }
    public WorkRequest viewWorkRequest(long workRequestId){return null;}

    public void addTechniciansToRequest(long workRequestId, List<Employee> employees){}

    public List<Employee>  viewTechniciansOnRequest(long workRequestId){
        return null;
    }

    public void changeState(State state){}

    public void escalateRequest(State state){}

    public void closeWorkRequest(long workRequestId){}

}
