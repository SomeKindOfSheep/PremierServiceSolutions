package com.pss.premierservicesolutions.repositories;

import com.pss.premierservicesolutions.entity.ServiceLevelAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceLevelAgreementRepository extends JpaRepository<ServiceLevelAgreement, Long> {

    @Query(
            value = "SELECT * FROM service_level_agreement sla WHERE sla.date_to <= NOW()",
            nativeQuery = true)
    List<ServiceLevelAgreement> findAllExpiredSlas();
}
