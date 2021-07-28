package com.pss.premierservicesolutions.services;


import com.pss.premierservicesolutions.entity.Call;
import com.pss.premierservicesolutions.entity.Complaint;
import com.pss.premierservicesolutions.entity.State;
import com.pss.premierservicesolutions.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientSatisfactionService {

    @Autowired
    ComplaintRepository complaintRepository;


    public Optional<Complaint> viewComplaint(long complaintId){
        return complaintRepository.findById(complaintId);
    }

    public void addCallToComplaint(Call call, long complaintId){}

    public void changeState(State state){}

    public void escalateRequest(State state){}

    public void createFollowUp(Complaint complaint){}
}
