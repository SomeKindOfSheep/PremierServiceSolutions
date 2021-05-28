package com.pss.premierservicesolutions.repositories;

import com.pss.premierservicesolutions.models.ServiceLevelAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceLevelAgreementRepository extends JpaRepository<ServiceLevelAgreement, Integer> {
}
