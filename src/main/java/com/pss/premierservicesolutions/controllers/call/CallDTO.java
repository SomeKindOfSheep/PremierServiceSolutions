package com.pss.premierservicesolutions.controllers.call;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CallDTO {

    private long id;
    private String callSummary;
    private boolean outgoing;
    private int duration;
    private LocalDate endTime;
    private long employeeId;

}
