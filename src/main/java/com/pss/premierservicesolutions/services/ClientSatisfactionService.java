package com.pss.premierservicesolutions.services;


import com.pss.premierservicesolutions.entity.Complaint;
import com.pss.premierservicesolutions.entity.FollowUp;
import com.pss.premierservicesolutions.entity.State;
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


    public Optional<Complaint> viewComplaint(long complaintId){
        return complaintRepository.findById(complaintId);
    }

    public Complaint addComplaint(Complaint complaint){
       return complaintRepository.saveAndFlush(complaint);
    }

    public List<FollowUp> getAllFollowUpsForComplaint(long complaintId){
            return complaintRepository.findById(complaintId).get().getFollowUp();
    }

    public Complaint updateComplaintState(State state, long complaintId) {
        Complaint complaint = viewComplaint(complaintId).get();
        complaint.setState(state);
        BeanUtils.copyProperties(complaint, complaint, "id");
        return complaintRepository.saveAndFlush(complaint);
    }

    public FollowUp updateFollowUpState(State state, long followUpId) {
        FollowUp followUp = followUpRespository.findById(followUpId).get();
        followUp.setState(state);
        BeanUtils.copyProperties(followUp, followUp, "id");
        return followUpRespository.saveAndFlush(followUp);
    }


    public Complaint addFollowUp(FollowUp followUp, long complaintId){
        Complaint complaint = viewComplaint(complaintId).get();
        FollowUp followUpReturn = followUpRespository.saveAndFlush(followUp);

        List<FollowUp> followUps = complaint.getFollowUp();
        followUps.add(followUpReturn);

        complaint.setFollowUp(followUps);

        BeanUtils.copyProperties(complaint, complaint, "id");

        return complaintRepository.saveAndFlush(complaint);
    }



    //public void addCallToComplaint(Call call, long complaintId){}

}

