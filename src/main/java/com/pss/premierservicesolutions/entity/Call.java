package com.pss.premierservicesolutions.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="call_from_call_centre")

public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "callSeq", sequenceName = "call_seq", schema = "premier_service_solutions", allocationSize = 1)
    private long id;

    @ManyToOne
    private Employee employee;

    @OneToOne
    private CallMetaData callMetaData;

    @Enumerated(EnumType.STRING)
    private State state;



}
