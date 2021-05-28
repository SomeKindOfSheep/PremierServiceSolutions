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
@Table(name="contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne
    private Employee employee;

    @OneToOne
    private ContractType contractType;

    @OneToOne
    private ServiceLevelAgreement serviceLevelAgreement;

    @Column(name = "date_from")
    private Date dateFrom;

    @Column(name = "date_to")
    private Date dateTo;


}
