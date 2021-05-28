package com.pss.premierservicesolutions.services;


import com.pss.premierservicesolutions.models.*;
import com.pss.premierservicesolutions.repositories.CallRepository;
import com.pss.premierservicesolutions.repositories.ClientRepository;
import com.pss.premierservicesolutions.repositories.ContractRepository;
import com.pss.premierservicesolutions.repositories.WorkRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        Employee employee;



        public void addCall(Call call){}

        public Call getCall(long callId){
                return callRepository.getOne(1);
        }

        public Client displayClientDetails(long clientId){
              return  clientRepository.getOne(1);
        }


        public void submitWorkRequest(WorkRequest workRequest){

        }

        public void addDetailsToWorkRequest(long workRequestId){
                workRequestRepository.getOne(1);
        }

        public List<Call> viewCallsForClient(long clientId){
                return callRepository.findAllById(clientId);
        }

        public List<Contract> viewClientContracts(long clientId){
                return contractRepository.findAllById(clientId);
        }

        public void endAndLogCall(Call call){}



}
