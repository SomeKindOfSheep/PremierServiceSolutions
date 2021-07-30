package com.pss.premierservicesolutions.repositories;

import com.pss.premierservicesolutions.entity.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {

    @Query(
            value = "SELECT * FROM contract_type ct WHERE ct.date_to <= NOW()",
            nativeQuery = true)
    List<ContractType> findAllExpiredContractTypes();


    @Query(
            value = "SELECT ct.id FROM contract_type ct",
            nativeQuery = true)
    List<Long> getAllContractTypeIds();
}
