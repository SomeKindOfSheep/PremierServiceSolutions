package com.pss.premierservicesolutions.services;

import com.pss.premierservicesolutions.controllers.clientMaintenance.MaintenanceDTO;
import com.pss.premierservicesolutions.entity.Call;
import com.pss.premierservicesolutions.entity.Client;
import com.pss.premierservicesolutions.entity.Contract;
import com.pss.premierservicesolutions.entity.exception.ResourceNotFoundException;
import com.pss.premierservicesolutions.repositories.ClientRepository;
import com.pss.premierservicesolutions.repositories.ContractRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class ClientMaintenanceService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ContractRepository contractRepository;

    public Client createClient(Client client, long contractId) throws ResourceNotFoundException {
        Client savedClient = clientRepository.saveAndFlush(client);
        List<Contract> contracts = new ArrayList<>();
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new ResourceNotFoundException("Contract with id " + contractId + " not found"));
        contracts.add(contract);

        savedClient.setContract(contracts);
        BeanUtils.copyProperties(savedClient,savedClient, "id");
        return clientRepository.saveAndFlush(savedClient);
    }

    public MaintenanceDTO getClientById(long clientId) throws ResourceNotFoundException {
        return mapDTO(clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client with id " + clientId + " not found")));
    }


    public MaintenanceDTO updateClientDetails(long clientId, Client client) throws ResourceNotFoundException {
        Client existingClient = clientRepository.getOne(clientId);
        client.setContract(existingClient.getContract());
        client.setCalls(existingClient.getCalls());
        BeanUtils.copyProperties(client,existingClient,"id");
        clientRepository.saveAndFlush(existingClient);
        return getClientById(clientId);
    }

    private MaintenanceDTO mapDTO (Client client){
        MaintenanceDTO dto = new MaintenanceDTO();
        dto.setId(client.getId());
        dto.setFullName(client.getFullName());
        dto.setAddress(client.getAddress());
        dto.setEMail(client.getEMail());
        dto.setTelephone(client.getTelephone());
        dto.setClientType(client.getClientType());
        List<Long> ids = new ArrayList<Long>();
        for (Call calls: client.getCalls()){
            ids.add(calls.getId());
        }
        dto.setCallIds(ids);
        dto.setContract(client.getContract());

        return dto;

    }

}
