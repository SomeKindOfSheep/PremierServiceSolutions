package com.pss.premierservicesolutions.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="work_request")
public class WorkRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "workRequestSeq", sequenceName = "work_request_seq", schema = "premier_service_solutions", allocationSize = 1)
    private long id;

    @OneToMany
    private List<Employee> employee;

    @OneToOne
    private Client client;

    @Column(name = "work_request_priority")
    @Enumerated(EnumType.STRING)
    private WorkRequestPriority workRequestPriority;

    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany
    private List<Call> calls;

    @OneToOne
    private WorkRequestMetaData workRequestMetaData;

}
