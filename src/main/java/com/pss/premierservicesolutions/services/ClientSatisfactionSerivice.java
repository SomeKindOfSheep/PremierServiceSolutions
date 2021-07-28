package com.pss.premierservicesolutions.services;


import com.pss.premierservicesolutions.entity.Call;
import com.pss.premierservicesolutions.entity.Complaint;
import com.pss.premierservicesolutions.entity.State;
import com.pss.premierservicesolutions.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientSatisfactionSerivice {

    @Autowired
    ComplaintRepository complaintRepository;


    public void makeCallToClient(long clientId){}

    public Complaint viewComplaint(long complaintId){
        return complaintRepository.getOne(complaintId);
    }

    public void addCallToComplaint(Call call, long complaintId){}

    public void changeState(State state){}

    public void escalateRequest(State state){}

    public void createFollowUp(Complaint complaint){}
}
