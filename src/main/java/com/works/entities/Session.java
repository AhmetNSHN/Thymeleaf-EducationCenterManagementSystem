package com.works.entities;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(schema = "\"rcm\"", name = "\"session\"")
public class Session
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_seq")
    @SequenceGenerator(name = "session_seq", initialValue = 1, allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tutor tutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Program program;

    //@JsonProperty(access = Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "session")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SessionParticipant> dersOgrenciler = new ArrayList<>();

    @Column
    private String schedule;

    @Column
    private String cost;

    @Override
    public String toString()
    {
        return "Ders [tutor=" + tutor + ", program=" + program + "]";
    }

}

