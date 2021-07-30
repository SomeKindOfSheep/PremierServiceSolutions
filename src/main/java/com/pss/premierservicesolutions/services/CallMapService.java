package com.pss.premierservicesolutions.services;

import com.pss.premierservicesolutions.controllers.call.CallDTO;
import com.pss.premierservicesolutions.entity.*;
import com.pss.premierservicesolutions.entity.exception.ResourceNotFoundException;
import com.pss.premierservicesolutions.repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CallMapService {

    @Autowired
    CallRepository callRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    WorkRequestRepository workRequestRepository;

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    ClientRepository clientRepository;

    public Optional<Call> getCallDetails(long callId) {
        return callRepository.findById(callId);
    }

    public CallDTO submitClientLinkedCall(Call call, long employeeId, long clientId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee with id " + employeeId + " not found"));
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client with id " + clientId + " not found"));
        call.setEmployee(employee);
        Call returnedCall = callRepository.saveAndFlush(call);

        List<Call> calls = client.getCalls();
        calls.add(returnedCall);
        BeanUtils.copyProperties(client, client, "id");
        clientRepository.saveAndFlush(client);

        return mapDto(call);
    }

    public CallDTO submitComplaintLinkedCall(Call call, long employeeId, long complaintId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee with id " + employeeId + " not found"));
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new ResourceNotFoundException("Complaint with id " + complaintId + " not found"));
        call.setEmployee(employee);
        Call returnedCall = callRepository.saveAndFlush(call);

        List<Call> calls = complaint.getCallsLinkedToComplaint();
        calls.add(returnedCall);
        BeanUtils.copyProperties(complaint, complaint, "id");
        complaintRepository.saveAndFlush(complaint);
        return mapDto(call);
    }

    public CallDTO submitNotLinkedCall(Call call, long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee with id " + employeeId + " not found"));
        call.setEmployee(employee);
        callRepository.saveAndFlush(call);
        return mapDto(call);
    }


    public CallDTO submitWorkLinkedCall(Call call, long employeeId, long workRequestId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee with id " + employeeId + " not found"));
        call.setEmployee(employee);
        BeanUtils.copyProperties(call, call, "id");
        Call returnCall = callRepository.saveAndFlush(call);

        WorkRequest workRequest = workRequestRepository.findById(workRequestId).orElseThrow(() -> new ResourceNotFoundException("Work request with id " + workRequestId + " not found"));
        List<Call> workRequestCalls = workRequest.getCalls();
        workRequestCalls.add(returnCall);
        BeanUtils.copyProperties(workRequest, workRequest, "id");

        Client client = clientRepository.findById(workRequest.getClient().getId()).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        List<Call> clientCalls = client.getCalls();
        clientCalls.add(returnCall);
        BeanUtils.copyProperties(client, client, "id");
        clientRepository.saveAndFlush(client);

        workRequestRepository.saveAndFlush(workRequest);

        return mapDto(returnCall);
    }

    private CallDTO mapDto(Call call){
        CallDTO callDTO = new CallDTO();
        callDTO.setId(call.getId());
        callDTO.setCallSummary(call.getSummaryOfCall());
        callDTO.setOutgoing(call.isOutgoing());
        callDTO.setDuration(call.getDuration());
        callDTO.setEmployeeId(call.getEmployee().getId());
        callDTO.setEndTime(call.getEndTime());

        return callDTO;
    }
}
