package com.pss.premierservicesolutions.repositories;

import com.pss.premierservicesolutions.models.WorkRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRequestRepository extends JpaRepository<WorkRequest, Integer> {
}
