package com.pss.premierservicesolutions.models;

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
    private long id;

    @OneToMany
    private List<Employee> employee;

    @OneToOne
    private Client client;

    @Column(name = "work_request_priority")
    private WorkRequestPriority workRequestPriority;

    private State state;

    @OneToMany
    private List<Call> calls;

    @OneToOne
    private WorkRequestMetaData workRequestMetaData;

}
