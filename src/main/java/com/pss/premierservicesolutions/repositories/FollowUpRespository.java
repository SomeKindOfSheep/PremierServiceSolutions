package com.pss.premierservicesolutions.repositories;

import com.pss.premierservicesolutions.entity.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUpRespository extends JpaRepository<FollowUp, Long> {
}
