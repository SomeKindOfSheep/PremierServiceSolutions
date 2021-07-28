package com.pss.premierservicesolutions.services;


import com.pss.premierservicesolutions.entity.Contract;
import com.pss.premierservicesolutions.entity.ServiceLevelAgreement;
import com.pss.premierservicesolutions.repositories.ContractRepository;
import com.pss.premierservicesolutions.repositories.ServiceLevelAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractMaintenanceService {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ServiceLevelAgreementRepository serviceLevelAgreementRepository;

    public void addContract(Contract contract) {

    }

    public Contract viewContract(long contractId) {

        return contractRepository.getOne(contractId);
    }

    public void removeContract(long contractId) {
    }


    public ServiceLevelAgreement createServiceLevelAgreement() {

        return ServiceLevelAgreement.builder().build();
    }

    public ServiceLevelAgreement viewServiceLevelAgreement(long slaId) {

        return serviceLevelAgreementRepository.getOne(slaId);
    }

    public void removeServiceLevelAgreement(long slaId) {
    }


    public void addSlaToContract(long contractId, long slaId) {
    }

    public Contract viewContractPerformance(long contractId) {

        return contractRepository.getOne(contractId);
    }
}
