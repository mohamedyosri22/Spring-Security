package com.spring.model;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long id;

    @Column(name="role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
