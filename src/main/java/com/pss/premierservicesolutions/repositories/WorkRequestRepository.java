package com.pss.premierservicesolutions.repositories;

import com.pss.premierservicesolutions.entity.WorkRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Deprecated
public interface WorkRequestRepository extends JpaRepository<WorkRequest, Long> {
}
