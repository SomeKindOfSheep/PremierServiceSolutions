package com.pss.premierservicesolutions.services;


import com.pss.premierservicesolutions.entity.*;
import com.pss.premierservicesolutions.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallCentreService {

        @Autowired
        CallRepository callRepository;

        @Autowired
        ClientRepository clientRepository;

        @Autowired
        WorkRequestRepository workRequestRepository;

        @Autowired
        ContractRepository contractRepository;

        @Autowired
        EmployeeRepository employeeRepository;




        public Call getCall(long callId){
                return callRepository.getOne(callId);
        }

        public Client displayClientDetails(long clientId){
              return  clientRepository.getOne(clientId);
        }

        public void addDetailsToWorkRequest(long workRequestId){
                workRequestRepository.getOne(workRequestId);
        }

        public List<Call> viewCallsForClient(long clientId){
                return callRepository.findAllById(clientId);
        }

        public List<Contract> viewClientContracts(long clientId){
                return contractRepository.findAllById(clientId);
        }

        public void endAndLogCall(Call call){
                callRepository.saveAndFlush(call);
        }



}
