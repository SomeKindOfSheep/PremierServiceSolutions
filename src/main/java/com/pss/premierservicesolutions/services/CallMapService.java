package com.pss.premierservicesolutions.services;

import com.pss.premierservicesolutions.entity.*;
import com.pss.premierservicesolutions.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CallMapService {

    @Autowired
    CallRepository callRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Optional<Call> getCallDetails(long callId) {
        return callRepository.findById(callId);
    }

    public Call submitCall(Call call, long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        call.setEmployee(employee);
        return callRepository.saveAndFlush(call);
    }

    // getclientById shows all calls
        /*public List<Call> viewCallsForClient(long clientId){
                return callRepository.findAllById(clientId);
        }*/


}
