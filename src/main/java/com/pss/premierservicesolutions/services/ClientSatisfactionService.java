package com.pss.premierservicesolutions.services;


import com.pss.premierservicesolutions.entity.Client;
import com.pss.premierservicesolutions.entity.Complaint;
import com.pss.premierservicesolutions.entity.FollowUp;
import com.pss.premierservicesolutions.entity.State;
import com.pss.premierservicesolutions.entity.exception.NoIdException;
import com.pss.premierservicesolutions.entity.exception.ResourceNotFoundException;
import com.pss.premierservicesolutions.repositories.ClientRepository;
import com.pss.premierservicesolutions.repositories.ComplaintRepository;
import com.pss.premierservicesolutions.repositories.FollowUpRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientSatisfactionService {

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    FollowUpRespository followUpRespository;

    @Autowired
    ClientRepository clientRepository;


    public Optional<Complaint> viewComplaint(long complaintId){
        return complaintRepository.findById(complaintId);
    }

    public Complaint addComplaint(Complaint complaint, long clientId) throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client with id " + clientId + " not found"));
        complaint.setClient(client);
        BeanUtils.copyProperties(complaint, complaint, "id");
       return complaintRepository.saveAndFlush(complaint);
    }
    // for future use
    public List<FollowUp> getAllFollowUpsForComplaint(long complaintId){
            return complaintRepository.findById(complaintId).get().getFollowUp();
    }

    public Complaint updateComplaintState(State state, long complaintId) throws ResourceNotFoundException {
        Complaint complaint = viewComplaint(complaintId).orElseThrow(() -> new ResourceNotFoundException("Complaint with id " + complaintId + " not found"));
        complaint.setState(state);
        BeanUtils.copyProperties(complaint, complaint, "id");
        return complaintRepository.saveAndFlush(complaint);
    }

    public FollowUp updateFollowUpState(State state, long followUpId) throws ResourceNotFoundException {
        FollowUp followUp = followUpRespository.findById(followUpId).orElseThrow(() -> new ResourceNotFoundException("Follow up with id " + followUpId + " not found"));
        followUp.setState(state);
        BeanUtils.copyProperties(followUp, followUp, "id");
        return followUpRespository.saveAndFlush(followUp);
    }

    public Complaint addFollowUp(FollowUp followUp, long complaintId) throws ResourceNotFoundException {
        Complaint complaint = viewComplaint(complaintId).orElseThrow(() -> new ResourceNotFoundException("Complaint up with id " + complaintId + " not found"));
        FollowUp followUpReturn = followUpRespository.saveAndFlush(followUp);

        List<FollowUp> followUps = complaint.getFollowUp();
        followUps.add(followUpReturn);

        complaint.setFollowUp(followUps);

        BeanUtils.copyProperties(complaint, complaint, "id");

        return complaintRepository.saveAndFlush(complaint);
    }


}

