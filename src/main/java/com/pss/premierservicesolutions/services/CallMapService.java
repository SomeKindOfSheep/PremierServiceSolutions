package com.pss.premierservicesolutions.services;

import com.pss.premierservicesolutions.controllers.call.CallDTO;
import com.pss.premierservicesolutions.entity.*;
import com.pss.premierservicesolutions.repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public CallDTO submitClientLinkedCall(Call call, long employeeId, long clientId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        Client client = clientRepository.findById(clientId).get();
        call.setEmployee(employee);
        Call returnedCall = callRepository.saveAndFlush(call);

        List<Call> calls = client.getCalls();
        calls.add(returnedCall);
        BeanUtils.copyProperties(client, client, "id");
        clientRepository.saveAndFlush(client);

        return mapDto(call);
    }

    public CallDTO submitComplaintLinkedCall(Call call, long employeeId, long complaintId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        Complaint complaint = complaintRepository.findById(complaintId).get();
        call.setEmployee(employee);
        Call returnedCall = callRepository.saveAndFlush(call);

        List<Call> calls = complaint.getCallsLinkedToComplaint();
        calls.add(returnedCall);
        BeanUtils.copyProperties(complaint, complaint, "id");
        complaintRepository.saveAndFlush(complaint);
        return mapDto(call);
    }

    public CallDTO submitNotLinkedCall(Call call, long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        call.setEmployee(employee);
        callRepository.saveAndFlush(call);
        return mapDto(call);
    }


    public CallDTO submitWorkLinkedCall(Call call, long employeeId, long workRequestId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        call.setEmployee(employee);
        BeanUtils.copyProperties(call, call, "id");
        Call returnCall = callRepository.saveAndFlush(call);

        WorkRequest workRequest = workRequestRepository.findById(workRequestId).get();
        List<Call> workRequestCalls = workRequest.getCalls();
        workRequestCalls.add(returnCall);
        BeanUtils.copyProperties(workRequest, workRequest, "id");

        Client client = clientRepository.findById(workRequest.getClient().getId()).get();
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
