package com.pss.premierservicesolutions.controllers.workRequest;

import com.pss.premierservicesolutions.entity.Client;
import com.pss.premierservicesolutions.entity.State;
import com.pss.premierservicesolutions.entity.WorkRequestPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Deprecated
public class WorkRequestDTO {

    private long id;
    private WorkRequestPriority workRequestPriority;
    private List<Long> callIds;
    private String descriptionOfProblem;
    private String descriptionOfSolution;
    private long estimatedTimeToCompletion;
    private List<Long> employeeId;
    private State state;
    private long clientId;

}
