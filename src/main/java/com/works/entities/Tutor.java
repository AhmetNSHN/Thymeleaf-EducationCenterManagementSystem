package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "\"rcm\"", name = "\"tutor\"")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tutor_seq")
    @SequenceGenerator(name = "tutor_seq", initialValue = 1, allocationSize = 1)
    private long id;

    @Column
    private String NameSurname;

    @Column
    private String occupation;
}
