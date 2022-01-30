package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "\"rcm\"", name = "\"session_participant\"")
public class SessionParticipant
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessionparticipant_seq")
    @SequenceGenerator(name = "sessionparticipant_seq", initialValue = 1, allocationSize = 1)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Session session;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Participant participant;

}
