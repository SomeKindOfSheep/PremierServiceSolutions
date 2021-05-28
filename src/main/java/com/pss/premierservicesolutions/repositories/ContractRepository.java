package com.pss.premierservicesolutions.repositories;

import com.pss.premierservicesolutions.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    List<Contract> findAllById(long clientId);
}
