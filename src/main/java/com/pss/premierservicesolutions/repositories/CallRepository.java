package com.pss.premierservicesolutions.repositories;

import com.pss.premierservicesolutions.models.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call, Integer> {
    List<Call> findAllById(long clientId);
}
