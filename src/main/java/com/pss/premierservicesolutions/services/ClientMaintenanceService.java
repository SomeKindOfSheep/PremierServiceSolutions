package com.pss.premierservicesolutions.services;


import com.pss.premierservicesolutions.models.Client;
import com.pss.premierservicesolutions.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientMaintenanceService {

    @Autowired
    ClientRepository clientRepository;

    public void addClient(Client client){}

    public Client viewClient(long clientId){

        return clientRepository.getOne(1);
    }


    public Client updateClientDetails(long clientId){ return clientRepository.getOne(1);}


    public void removeClient(Client client){
        clientRepository.delete(client);
    }

}
