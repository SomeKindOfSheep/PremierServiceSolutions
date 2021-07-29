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

    @Column(name = "work_request_priority")
    @Enumerated(EnumType.STRING)
    private WorkRequestPriority workRequestPriority;

    @OneToMany
    private List<Call> calls;

    @Column(name = "description_of_problem")
    private String descriptionOfProblem;

    @Column(name = "description_of_solution")
    private String descriptionOfSolution;

    @Column(name = "estimated_time_to_completion")
    private long estimatedTimeToCompletion;

    @OneToMany
    private List<Employee> employee;

    @OneToOne
    private Client client;

    @Enumerated(EnumType.STRING)
    private State state;

}
