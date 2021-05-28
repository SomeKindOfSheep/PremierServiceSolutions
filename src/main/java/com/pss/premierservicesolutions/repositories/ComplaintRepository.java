package com.pss.premierservicesolutions.repositories;

import com.pss.premierservicesolutions.models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
}
