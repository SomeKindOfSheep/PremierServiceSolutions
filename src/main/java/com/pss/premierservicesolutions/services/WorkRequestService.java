package com.pss.premierservicesolutions.services;

import com.pss.premierservicesolutions.controllers.workRequest.WorkRequestDTO;
import com.pss.premierservicesolutions.entity.*;
import com.pss.premierservicesolutions.repositories.ClientRepository;
import com.pss.premierservicesolutions.repositories.EmployeeRepository;
import com.pss.premierservicesolutions.repositories.WorkRequestRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Deprecated
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

    public WorkRequestDTO viewWorkRequestById(long workRequestId) {

        return mapDTO(workRequestRepository.findById(workRequestId).get());
    }

    private Optional<WorkRequest> getWorkRequestById(long workRequestId) {

        return workRequestRepository.findById(workRequestId);
    }

    private WorkRequestDTO mapDTO(WorkRequest workRequest){
        WorkRequestDTO dto = new WorkRequestDTO();
        dto.setId(workRequest.getId());
        dto.setDescriptionOfProblem(workRequest.getDescriptionOfProblem());
        dto.setDescriptionOfSolution(workRequest.getDescriptionOfSolution());
        dto.setEstimatedTimeToCompletion(workRequest.getEstimatedTimeToCompletion());
        dto.setState(workRequest.getState());
        dto.setClientId(workRequest.getClient().getId());

        List<Long> employeeIds = new ArrayList<>();
        for (Employee employee: workRequest.getEmployee() ){
            employeeIds.add(employee.getId());
        }
        List<Long> callids = new ArrayList<>();
        for (Call calls: workRequest.getCalls() ){
            callids.add(calls.getId());
        }

        dto.setCallIds(callids);
        dto.setEmployeeId(employeeIds);

        return dto;
    }

    public WorkRequest addTechniciansToRequest(long workRequestId, long technicianId) {
        WorkRequest workRequest = getWorkRequestById(workRequestId).get();
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

        WorkRequest workRequest = getWorkRequestById(workRequestId).get();
        workRequest.setState(state);
        BeanUtils.copyProperties(workRequest, workRequest, "id");
        return workRequestRepository.saveAndFlush(workRequest);
    }

    public WorkRequest changePriority(WorkRequestPriority priority, long workRequestId ) {
        WorkRequest workRequest = getWorkRequestById(workRequestId).get();
        workRequest.setWorkRequestPriority(priority);
        BeanUtils.copyProperties(workRequest, workRequest, "id");
        return workRequestRepository.saveAndFlush(workRequest);
    }

    // part of the change state
    /*public void closeWorkRequest(long workRequestId) {
    }*/

}
