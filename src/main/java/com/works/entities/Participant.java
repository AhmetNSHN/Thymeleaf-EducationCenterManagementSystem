package com.works.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(schema = "\"rcm\"", name = "\"participant\"")
public class Participant
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_seq")
    @SequenceGenerator(name = "participant_seq", initialValue = 1, allocationSize = 1)
    private long id;

    @Column(length = 50)
    private String firstLastName;

    @Column
    private String dateOfBirth;

    @Column
    private String ssn;

    @Column(length = 250)
    private String description;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "participant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SessionParticipant> sessionParticipantList = new ArrayList<>();

}

