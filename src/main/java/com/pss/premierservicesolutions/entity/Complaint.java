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
@Table(name="complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "complaintSeq", sequenceName = "complaint_seq", schema = "premier_service_solutions", allocationSize = 1)
    private long id;
    @OneToMany
    private List<Call> callsLinkedToComplaint;

    @Enumerated(EnumType.STRING)
    private State state;


}
