package com.pss.premierservicesolutions.services;


import com.pss.premierservicesolutions.entity.Contract;
import com.pss.premierservicesolutions.entity.ContractType;
import com.pss.premierservicesolutions.entity.Employee;
import com.pss.premierservicesolutions.entity.ServiceLevelAgreement;
import com.pss.premierservicesolutions.entity.exception.ResourceNotFoundException;
import com.pss.premierservicesolutions.repositories.ContractRepository;
import com.pss.premierservicesolutions.repositories.ContractTypeRepository;
import com.pss.premierservicesolutions.repositories.EmployeeRepository;
import com.pss.premierservicesolutions.repositories.ServiceLevelAgreementRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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


    public Contract createContract(Contract contract, long contractTypeId, long employeeId, long slaId) throws ResourceNotFoundException {

        ContractType contractType = contractTypeRepository.findById(contractTypeId).orElseThrow(() -> new ResourceNotFoundException("Contract with id " + contractTypeId + " not found"));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee with id " + employeeId + " not found"));
        ServiceLevelAgreement sla = serviceLevelAgreementRepository.findById(slaId).orElseThrow(() -> new ResourceNotFoundException("Sla with id " + slaId + " not found"));

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

    public Contract removeContract(long contractId) throws ResourceNotFoundException {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new ResourceNotFoundException("Contract with id " + contractId + " not found"));
        contract.setDateTo(LocalDate.now());
        BeanUtils.copyProperties(contract, contract, "id");
        return contractRepository.saveAndFlush(contract);
    }

    public Contract addSlaToContract(long contractId, long slaId) throws ResourceNotFoundException {
        Contract contract = viewContract(contractId).orElseThrow(() -> new ResourceNotFoundException("Contract with id " + contractId + " not found"));
        ServiceLevelAgreement sla = serviceLevelAgreementRepository.findById(slaId).orElseThrow(() -> new ResourceNotFoundException("Sla with id " + slaId + " not found"));
        contract.setServiceLevelAgreement(sla);
        BeanUtils.copyProperties(contract, contract, "id");
        return contractRepository.saveAndFlush(contract);
    }


}
