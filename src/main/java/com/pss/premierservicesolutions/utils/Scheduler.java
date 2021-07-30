package com.pss.premierservicesolutions.utils;

import com.pss.premierservicesolutions.entity.Contract;
import com.pss.premierservicesolutions.entity.ContractType;
import com.pss.premierservicesolutions.entity.ServiceLevelAgreement;
import com.pss.premierservicesolutions.repositories.ContractRepository;
import com.pss.premierservicesolutions.repositories.ContractTypeRepository;
import com.pss.premierservicesolutions.repositories.ServiceLevelAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@EnableAsync
public class Scheduler {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ServiceLevelAgreementRepository serviceLevelAgreementRepository;

    @Autowired
    ContractTypeRepository contractTypeRepository;


    //this runs every 24 hours
    //@Scheduled(fixedRate = 86400000)
    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduledTask(){
        findAllExpiredContracts();
        findAllExpiredSlas();
        findAllExpiredContractTypes();
    }

    private void findAllExpiredContracts(){
        List<Contract> listOfExpiredContracts = contractRepository.findAllExpiredContracts();
        System.out.println("No of expired contracts :"+listOfExpiredContracts.size());
    }

    private void findAllExpiredSlas(){
        List<ServiceLevelAgreement> listOfExpiredSlas = serviceLevelAgreementRepository.findAllExpiredSlas();
        System.out.println("No of expired service level agreements :"+listOfExpiredSlas.size());
    }

    private void findAllExpiredContractTypes(){
        List<ContractType> listOfExpiredContractTypes= contractTypeRepository.findAllExpiredContractTypes();
        System.out.println("No of expired service level agreements :"+listOfExpiredContractTypes.size());
    }
}
