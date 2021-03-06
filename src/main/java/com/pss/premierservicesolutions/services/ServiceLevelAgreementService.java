package com.pss.premierservicesolutions.services;

import com.pss.premierservicesolutions.entity.ServiceLevelAgreement;
import com.pss.premierservicesolutions.repositories.ServiceLevelAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceLevelAgreementService {

    @Autowired
    ServiceLevelAgreementRepository serviceLevelAgreementRepository;

    public List<ServiceLevelAgreement> getAllSla(){
        return serviceLevelAgreementRepository.findAll();
    }

    public Optional<ServiceLevelAgreement> getSlaById(long id){
        return serviceLevelAgreementRepository.findById(id);
    }

    public ServiceLevelAgreement createSla(ServiceLevelAgreement serviceLevelAgreement){
        return serviceLevelAgreementRepository.saveAndFlush(serviceLevelAgreement);
    }
}
