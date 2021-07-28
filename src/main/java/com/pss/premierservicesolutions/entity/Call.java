package com.pss.premierservicesolutions.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="call_map")
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "callSeq", sequenceName = "call_seq", schema = "premier_service_solutions", allocationSize = 1)
    private long id;

    @ManyToOne
    private Employee employee;

    @Column(name = "summary_of_call")
    private String summaryOfCall;

    @Column(name = "outgoing")
    private boolean outgoing;

    @Column(name = "duration")
    private int duration;

    @Column(name = "end_time")
    @CreationTimestamp
    private Date endTime;

}
