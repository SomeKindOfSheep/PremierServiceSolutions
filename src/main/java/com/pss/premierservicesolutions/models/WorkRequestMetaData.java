package com.pss.premierservicesolutions.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="work_request_meta_data")
public class WorkRequestMetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description_of_problem")
    private String descriptionOfProblem;
    @Column(name = "description_of_solution")
    private String descriptionOfSolution;
    @Column(name = "estimated_time_to_completion")
    private long estimatedTimeToCompletion;

}
