package com.pss.premierservicesolutions.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="follow_up")
public class FollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "followUpSeq", sequenceName = "follow_up_seq", schema = "premier_service_solutions", allocationSize = 1)
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "resolution")
    private String resolution;

    @Enumerated(EnumType.STRING)
    private State state;
}
