package com.pss.premierservicesolutions.services;


import com.pss.premierservicesolutions.entity.Contract;
import com.pss.premierservicesolutions.entity.ContractType;
import com.pss.premierservicesolutions.entity.Employee;
import com.pss.premierservicesolutions.entity.ServiceLevelAgreement;
import com.pss.premierservicesolutions.repositories.ContractRepository;
import com.pss.premierservicesolutions.repositories.ContractTypeRepository;
import com.pss.premierservicesolutions.repositories.EmployeeRepository;
import com.pss.premierservicesolutions.repositories.ServiceLevelAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractMaintenanceService {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ServiceLevelAgreementRepository serviceLevelAgreementRepository;

    @Autowired
    ContractTypeRepository contractTypeRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    public Contract createContract(Contract contract, long contractTypeId, long employeeId, long slaId) {

        /*Employee employee = employeeRepository.findById(employeeId).get();
        call.setEmployee(employee);
        return callRepository.saveAndFlush(call);*/

        ContractType contractType = contractTypeRepository.findById(contractTypeId).get();
        Employee employee = employeeRepository.findById(employeeId).get();
        ServiceLevelAgreement sla = serviceLevelAgreementRepository.findById(slaId).get();

        contract.setContractType(contractType);
        contract.setEmployee(employee);
        contract.setServiceLevelAgreement(sla);
        return contractRepository.saveAndFlush(contract);
    }

    public ContractType addContractType(ContractType contract) {
        return contractTypeRepository.saveAndFlush(contract);
    }

    public Optional<ContractType> getContractType(long typeId) {
       return contractTypeRepository.findById(typeId);
    }

    public Optional<Contract> viewContract(long contractId) {
        return contractRepository.findById(contractId);
    }

    public void removeContract(long contractId) {
    }

    public void addSlaToContract(long contractId, long slaId) {
    }

    public Contract viewContractPerformance(long contractId) {

        return contractRepository.getOne(contractId);
    }
}
