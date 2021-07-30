package com.pss.premierservicesolutions.repositories;

import com.pss.premierservicesolutions.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query(
            value = "SELECT * FROM contract ct WHERE ct.date_to <= NOW()",
            nativeQuery = true)
    List<Contract> findAllExpiredContracts();

    @Query(
            value = "SELECT * FROM contract ct WHERE ct.date_to >= NOW()",
            nativeQuery = true)
    List<Contract> findAllActiveContracts();

}
