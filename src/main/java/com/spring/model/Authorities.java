package com.spring.model;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name="authorities")
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="auth_id")
    private Long id;

    @Column(name="auth_name")
    private String authoritieName;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}
