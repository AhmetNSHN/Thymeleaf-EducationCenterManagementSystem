package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(schema = "\"rcm\"", name = "\"users\"")
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String username = "";

    @Column(length = 150, nullable = false)
    private String password = "";

    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<Role>();
}
