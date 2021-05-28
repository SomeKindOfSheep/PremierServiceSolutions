package com.pss.premierservicesolutions.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="call_meta_data")
public class CallMetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "summary_of_call")
    private String summaryOfCall;
    @Column(name = "outgoing")
    private boolean outgoing;
}
