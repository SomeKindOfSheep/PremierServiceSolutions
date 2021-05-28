package com.pss.premierservicesolutions.services;


import com.pss.premierservicesolutions.models.Call;
import com.pss.premierservicesolutions.models.Complaint;
import com.pss.premierservicesolutions.models.State;
import com.pss.premierservicesolutions.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientSatisfactionSerivice {

    @Autowired
    ComplaintRepository complaintRepository;


    public void makeCallToClient(long clientId){}

    public Complaint viewComplaint(long complaintId){
        return complaintRepository.getOne(1);
    }

    public void addCallToComplaint(Call call, long complaintId){}

    public void changeState(State state){}

    public void escalateRequest(State state){}

    public void createFollowUp(Complaint complaint){}
}
