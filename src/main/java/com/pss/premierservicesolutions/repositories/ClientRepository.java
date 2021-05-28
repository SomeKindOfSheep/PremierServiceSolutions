package com.pss.premierservicesolutions.repositories;


import com.pss.premierservicesolutions.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
