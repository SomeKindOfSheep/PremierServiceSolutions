package com.pss.premierservicesolutions.services;


import com.pss.premierservicesolutions.entity.Client;
import com.pss.premierservicesolutions.repositories.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientMaintenanceService {

    @Autowired
    ClientRepository clientRepository;

    public void addClient(Client client){
        clientRepository.saveAndFlush(client);
    }

    public Optional<Client> getClientById(long clientId){
        return clientRepository.findById(clientId);
    }


    public Client updateClientDetails(long clientId, Client client){
        Client existingClient = clientRepository.getOne(clientId);
        BeanUtils.copyProperties(client,existingClient,"id");
        return clientRepository.saveAndFlush(existingClient);
    }

}
