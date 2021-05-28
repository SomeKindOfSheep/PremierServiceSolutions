package com.pss.premierservicesolutions.models;


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
    private long id;

    @ManyToOne
    private Employee employee;

    @OneToOne
    private CallMetaData callMetaData;



}
