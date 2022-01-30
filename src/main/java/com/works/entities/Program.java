package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "\"rcm\"", name = "\"program\"")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "program_seq")
    @SequenceGenerator(name = "program_seq", initialValue = 1, allocationSize = 1)
    private long id;

    @Column
    private String title;

    @Column
    private String courseType;

    @Column
    private String topicsDescription;
}
